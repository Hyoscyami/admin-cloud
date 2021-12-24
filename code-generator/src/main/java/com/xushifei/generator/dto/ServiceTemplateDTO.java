package com.xushifei.generator.dto;

import lombok.Data;

/**
 * service模板生成
 *
 * @author xushifei
 * @date 2021/12/23
 */
@Data
public class ServiceTemplateDTO {
  /** import base service */
  private String baseServiceCompleteName;
  /** import base service impl */
  private String baseServiceImplCompleteName;
}
