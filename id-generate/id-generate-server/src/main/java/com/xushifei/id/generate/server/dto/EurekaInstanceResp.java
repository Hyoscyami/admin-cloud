package com.xushifei.id.generate.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * eureka响应信息
 *
 * @author xushifei
 * @date 2022/1/12
 */
@Data
public class EurekaInstanceResp {
  /** 实例hostname */
  private String hostname;
  /** 实例服务名称 */
  private String app;
  /** 实例ip */
  @JsonProperty("ipAddr")
  private String ip;
  /** 端口对象 */
  private Port port;
  /** 运行状态，up/down */
  private String status;

  static class Port {
    /** 服务端口 */
    @JsonProperty("$")
    private String port;
  }
}
