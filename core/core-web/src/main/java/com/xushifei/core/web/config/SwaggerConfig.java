package com.xushifei.core.web.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
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

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info();
    info.setTitle(properties.getTitle());
    info.setDescription(properties.getDescription());
    info.setVersion(properties.getVersion());
    info.setLicense(
        new License().name(properties.getLicenseName()).url(properties.getLicenseUrl()));
    return new OpenAPI().info(info);
  }
}
