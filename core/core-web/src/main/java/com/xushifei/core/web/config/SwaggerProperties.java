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
  /** 标题 */
  private String title;
  /** 描述 */
  private String description;
  /** 版本 */
  private String version = "1.0";
  /** 许可协议名称 */
  private String licenseName = "Apache 2.0";
  /** 许可协议地址 */
  private String licenseUrl = "https://www.apache.org/";
}
