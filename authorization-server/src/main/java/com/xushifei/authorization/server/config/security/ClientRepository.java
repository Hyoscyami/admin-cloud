package com.xushifei.authorization.server.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.stereotype.Service;

/**
 * client持久化
 *
 * @author xushifei
 * @date 2021/11/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientRepository implements RegisteredClientRepository {
  /**
   * Saves the registered client.
   *
   * @param registeredClient the {@link RegisteredClient}
   */
  @Override
  public void save(RegisteredClient registeredClient) {
    log.info("save registeredClient:{}", registeredClient);
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
    log.info("findById:{}", id);
    return RegisteredClient.withId("1")
        .clientId("admin")
        .clientSecret("{noop}123456")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .redirectUri("http://127.0.0.1:8763/login/oauth2/code/admin-oidc")
        .redirectUri("http://127.0.0.1:8763/authorized")
        .scope(OidcScopes.OPENID)
        .scope("message.read")
        .scope("message.write")
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build();
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
    log.info("findByClientId:{}", clientId);
    return RegisteredClient.withId("1")
        .clientId(clientId)
        .clientSecret("{noop}123456")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .redirectUri("http://127.0.0.1:8763/login/oauth2/code/admin-oidc")
        .redirectUri("http://127.0.0.1:8763/authorized")
        .scope(OidcScopes.OPENID)
        .scope("message.read")
        .scope("message.write")
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build();
  }
}
