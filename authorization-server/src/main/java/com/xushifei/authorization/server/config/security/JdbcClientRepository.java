package com.xushifei.authorization.server.config.security;

import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.support.ClientSupport;
import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import com.xushifei.common.utils.ConditionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * client持久化
 *
 * @author xushifei
 * @date 2021/11/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcClientRepository implements RegisteredClientRepository {
  private final ClientService clientService;
  private final ClientSupport clientSupport;
  /**
   * Saves the registered client.
   *
   * @param registeredClient the {@link RegisteredClient}
   */
  @Override
  public void save(RegisteredClient registeredClient) {
    throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "暂不支持动态注册客户端");
    // Client client = this.convertToClient(registeredClient);
    // clientSupport.saveOrUpdate(client);
  }

  /**
   * 转入库实体
   *
   * @param registeredClient
   * @return
   */
  private Client convertToClient(RegisteredClient registeredClient) {
    Client client = new Client();
    ConditionUtils.acceptIfNotBlank(registeredClient.getId(), id -> client.setId(Long.valueOf(id)));
    BeanUtils.copyProperties(registeredClient, client);
    client.setAuthenticationMethod(
        StringUtils.collectionToCommaDelimitedString(
            registeredClient.getClientAuthenticationMethods()));
    client.setAuthorizationGrantType(
        StringUtils.collectionToCommaDelimitedString(
            registeredClient.getAuthorizationGrantTypes()));
    registeredClient.getRedirectUris().stream().findFirst().ifPresent(client::setRedirectUri);
    ClientSettings clientSettings = registeredClient.getClientSettings();
    if (Objects.nonNull(clientSettings)) {
      client.setRequireProofKey(clientSettings.isRequireProofKey());
      client.setAuthorizationConsent(clientSettings.isRequireAuthorizationConsent());
    }
    TokenSettings tokenSettings = registeredClient.getTokenSettings();
    if (Objects.nonNull(tokenSettings)) {
      client.setAccessTokenTimeToLive((int) tokenSettings.getAccessTokenTimeToLive().toMinutes());
      client.setReuseRefreshTokens(tokenSettings.isReuseRefreshTokens());
      client.setRefreshTokenTimeToLive((int) tokenSettings.getRefreshTokenTimeToLive().toMinutes());
      client.setSignatureAlgorithm(tokenSettings.getIdTokenSignatureAlgorithm().getName());
    }
    return client;
  }

  /**
   * Returns the registered client identified by the provided {@code id}, or {@code null} if not
   * found.
   *
   * @param id the registration identifier
   * @return the {@link RegisteredClient} if found, otherwise {@code null}
   */
  @Override
  public RegisteredClient findById(String id) {
    return this.convertToRegisteredClient(clientSupport.getById(id));
  }

  /**
   * 转授权服务格式
   *
   * @param client
   * @return
   */
  private RegisteredClient convertToRegisteredClient(Client client) {
    // 客户端基本信息
    RegisteredClient.Builder builder = RegisteredClient.withId(String.valueOf(client.getId()));
    builder.clientId(client.getClientId()).clientSecret(client.getClientSecret());
    StringUtils.commaDelimitedListToSet(client.getAuthenticationMethod()).stream()
        .map(this::getAuthenticationMethodByValue)
        .forEach(builder::clientAuthenticationMethod);
    StringUtils.commaDelimitedListToSet(client.getAuthorizationGrantType()).stream()
        .map(this::getGrantTypeByValue)
        .forEach(builder::authorizationGrantType);
    builder.redirectUri(client.getRedirectUri());
    List<String> scopeValues =
        clientService.listScopesByClientId(client.getId()).stream()
            .map(Scope::getValue)
            .collect(Collectors.toList());
    builder.scopes(scopes -> scopes.addAll(scopeValues));

    // 客户端设置
    ClientSettings.Builder clientSettingsBuilder = ClientSettings.builder();
    ConditionUtils.acceptIfNotNull(
        client.getAuthorizationConsent(), clientSettingsBuilder::requireAuthorizationConsent);
    ConditionUtils.acceptIfNotNull(
        client.getRequireProofKey(), clientSettingsBuilder::requireProofKey);
    builder.clientSettings(clientSettingsBuilder.build());

    return builder.build();
  }

  /**
   * 字符转授权方式枚举
   *
   * @param value
   * @return
   */
  private AuthorizationGrantType getGrantTypeByValue(final String value) {
    if (Objects.equals(AuthorizationGrantType.AUTHORIZATION_CODE.getValue(), value)) {
      return AuthorizationGrantType.AUTHORIZATION_CODE;
    }
    if (Objects.equals(AuthorizationGrantType.IMPLICIT.getValue(), value)) {
      return AuthorizationGrantType.IMPLICIT;
    }
    if (Objects.equals(AuthorizationGrantType.REFRESH_TOKEN.getValue(), value)) {
      return AuthorizationGrantType.REFRESH_TOKEN;
    }
    if (Objects.equals(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue(), value)) {
      return AuthorizationGrantType.CLIENT_CREDENTIALS;
    }
    if (Objects.equals(AuthorizationGrantType.PASSWORD.getValue(), value)) {
      return AuthorizationGrantType.PASSWORD;
    }
    if (Objects.equals(AuthorizationGrantType.JWT_BEARER.getValue(), value)) {
      return AuthorizationGrantType.JWT_BEARER;
    }
    throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), "暂不支持该授权方式");
  }
  /**
   * 字符转认证方式枚举
   *
   * @param value
   * @return
   */
  private ClientAuthenticationMethod getAuthenticationMethodByValue(final String value) {
    if (Objects.equals(ClientAuthenticationMethod.BASIC.getValue(), value)) {
      return ClientAuthenticationMethod.BASIC;
    }
    if (Objects.equals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue(), value)) {
      return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
    }
    if (Objects.equals(ClientAuthenticationMethod.POST.getValue(), value)) {
      return ClientAuthenticationMethod.POST;
    }
    if (Objects.equals(ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue(), value)) {
      return ClientAuthenticationMethod.CLIENT_SECRET_POST;
    }
    if (Objects.equals(ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue(), value)) {
      return ClientAuthenticationMethod.CLIENT_SECRET_JWT;
    }
    if (Objects.equals(ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue(), value)) {
      return ClientAuthenticationMethod.PRIVATE_KEY_JWT;
    }
    if (Objects.equals(ClientAuthenticationMethod.NONE.getValue(), value)) {
      return ClientAuthenticationMethod.NONE;
    }
    throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), "暂不支持该认证方式");
  }
  /**
   * Returns the registered client identified by the provided {@code clientId}, or {@code null} if
   * not found.
   *
   * @param clientId the client identifier
   * @return the {@link RegisteredClient} if found, otherwise {@code null}
   */
  @Override
  public RegisteredClient findByClientId(String clientId) {
    Client client = clientSupport.lambdaQuery().eq(Client::getClientId, clientId).one();
    return this.convertToRegisteredClient(client);
  }
}
