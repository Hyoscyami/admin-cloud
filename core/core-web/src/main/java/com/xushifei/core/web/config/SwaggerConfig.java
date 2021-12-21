package com.xushifei.core.web.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * swagger配置
 *
 * @author xushifei
 * @date 2021/12/17
 */
@Data
@Profile({"!prod"})
@Configuration
@ConditionalOnProperty(
    name = {"swagger.enable"},
    havingValue = "true")
@RequiredArgsConstructor
public class SwaggerConfig {
  private final SwaggerProperties properties;
}
