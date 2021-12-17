package com.xushifei.core.web.config;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置
 *
 * @author xushifei
 * @date 2021/12/17
 */
@Data
@Profile({"!prod"})
@EnableOpenApi
@Configuration
@ConditionalOnProperty(
    name = {"swagger.enable"},
    havingValue = "true")
@RequiredArgsConstructor
@ApiModel(value = "", description = "")
public class SwaggerConfig {
  private final SwaggerProperties properties;

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.basePackage(this.properties.getController()))
        .build();
  }
}
