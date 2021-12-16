package com.xushifei.authorization.server.config.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.xushifei.authorization.server.utils.KeyGeneratorUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

/**
 * 授权服务器配置
 *
 * @author xushifei
 * @date 2021/11/17
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
  /**
   * 安全过滤器
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
      throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    return http.formLogin(Customizer.withDefaults()).build();
  }

  /**
   * client持久化
   *
   * @return
   */
  @Bean
  public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient registeredClient =
        RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("admin")
            .clientSecret("{noop}123456")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .redirectUri("http://127.0.0.1:8080/login/oauth2/code/admin-oidc")
            .redirectUri("http://127.0.0.1:8080/authorized")
            .scope(OidcScopes.OPENID)
            .scope("message.read")
            .scope("message.write")
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build();
    return new InMemoryRegisteredClientRepository(registeredClient);
  }

  /**
   * Json Web 秘钥
   *
   * @return
   */
  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    RSAKey rsaKey = KeyGeneratorUtils.generateRsa();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public ProviderSettings providerSettings() {
    return ProviderSettings.builder().issuer("http://auth-server:8764").build();
  }
}
