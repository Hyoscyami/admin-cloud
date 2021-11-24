package com.xushifei.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author xushifei
 * @date 2021/11/17
 */
@EnableWebSecurity
public class DefaultSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
        .formLogin(withDefaults());
    return http.build();
  }
}
