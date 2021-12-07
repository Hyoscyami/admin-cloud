package com.xushifei.authorization.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.xushifei.authorization.service.InMemoryOAuth2AuthorizationConsentService;
import com.xushifei.authorization.service.InMemoryOAuth2AuthorizationService;
import com.xushifei.authorization.utils.KeyGeneratorUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 授权服务器配置
 *
 * @author xushifei
 * @date 2021/11/17
 */
@EnableWebSecurity
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
    return new ClientRepository();
  }

  /**
   * 授权信息业务类
   *
   * @return
   */
  @Bean
  public OAuth2AuthorizationService authorizationService() {
    return new InMemoryOAuth2AuthorizationService();
  }

  /**
   * 自定义授权业务类
   *
   * @return
   */
  @Bean
  public OAuth2AuthorizationConsentService authorizationConsentService() {
    return new InMemoryOAuth2AuthorizationConsentService();
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
  UserDetailsService users() {
    UserDetails user =
        User.withDefaultPasswordEncoder()
            .username("user1")
            .password("password")
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
  }
}
