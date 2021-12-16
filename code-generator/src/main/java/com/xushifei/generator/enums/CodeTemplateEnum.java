package com.xushifei.generator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代码生成模板枚举
 *
 * @author xushifei
 * @date 2021/12/16
 */
@Getter
@AllArgsConstructor
public enum CodeTemplateEnum {
  /** 新增DTO类的模板名称 */
  ADD_DTO_CLASS_NAME("Add%sReq"),
  /** 生成模板路径 */
  ENTITY_TEMPLATE_PATH("/templates/code-generate/entity.java"),
  CONTROLLER_TEMPLATE_PATH("/templates/code-generate/entity.java"),
  ADD_DTO_TEMPLATE_PATH("/templates/code-generate/add.java.ftl");
  private final String value;
}
