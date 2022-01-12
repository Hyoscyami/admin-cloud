package com.xushifei.id.generate.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * eureka返回信息
 *
 * @author xushifei
 * @date 2022/1/12
 */
@Data
public class EurekaApplicationResp {
  /** 服务名，即spring.application.name */
  private String name;
}
