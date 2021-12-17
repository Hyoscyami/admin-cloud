package com.xushifei.core.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * swagger属性配置文件
 *
 * @author xushifei
 * @date 2021/12/17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger", ignoreInvalidFields = true)
public class SwaggerProperties {
  /** 是否开启swagger */
  private Boolean enable;
  /** controller base package */
  private String controller;
}
