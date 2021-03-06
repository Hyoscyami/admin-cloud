package com.xushifei.authorization.server.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 默认安全配置
 *
 * @author xushifei
 * @date 2021/12/8
 */
@EnableWebSecurity
public class DefaultSecurityConfig {
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(
            authorizeRequests ->
                authorizeRequests
                    .antMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .formLogin(withDefaults());
    return http.build();
  }
}
