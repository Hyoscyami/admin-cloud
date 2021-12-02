package com.xushifei.authorization.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;

import java.util.UUID;

/**
 * client持久化
 *
 * @author xushifei
 * @date 2021/11/26
 */
@Slf4j
public class ClientRepository implements RegisteredClientRepository {
  /**
   * Saves the registered client.
   *
   * @param registeredClient the {@link RegisteredClient}
   */
  @Override
  public void save(RegisteredClient registeredClient) {}

  /**
   * Returns the registered client identified by the provided {@code id}, or {@code null} if not
   * found.
   *
   * @param id the registration identifier
   * @return the {@link RegisteredClient} if found, otherwise {@code null}
   */
  @Override
  public RegisteredClient findById(String id) {
    return RegisteredClient.withId(id)
        .clientId("messaging-client")
        .clientSecret("{noop}secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
        .redirectUri("http://127.0.0.1:8080/authorized")
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
    return RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId(clientId)
        .clientSecret("{noop}secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
        .redirectUri("http://127.0.0.1:8080/authorized")
        .scope(OidcScopes.OPENID)
        .scope("message.read")
        .scope("message.write")
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build();
  }
}
