package com.xushifei.admin.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * oauth2登录
 *
 * @author xushifei
 * @date 2021/11/15
 */
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
        .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/admin"))
        .oauth2Client(withDefaults());
  }
}
