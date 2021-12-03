package com.xushifei.uims.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 资源服务器安全配置
 *
 * @author xushifei
 * @date 2021/12/3
 */
@EnableWebSecurity
public class ResourceServerConfig {
  /**
   * security过滤器
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.mvcMatcher("/messages/**")
        .authorizeRequests()
        .mvcMatchers("/messages/**")
        .access("hasAuthority('SCOPE_message.read')")
        .and()
        .oauth2ResourceServer()
        .jwt();
    return http.build();
  }
}
