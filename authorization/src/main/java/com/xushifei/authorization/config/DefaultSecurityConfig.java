package com.xushifei.authorization.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * spring security安全配置
 *
 * @author xushifei
 * @date 2021/11/17
 */
@EnableWebSecurity
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
        .formLogin(withDefaults());
  }
}
