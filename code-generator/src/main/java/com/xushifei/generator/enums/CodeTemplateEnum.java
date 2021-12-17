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
  UPDATE_DTO_CLASS_NAME("Update%sReq"),
  QUERY_DTO_CLASS_NAME("Query%sReq"),
  VO_CLASS_NAME("%sVO"),
  /** 生成模板路径 */
  ENTITY_TEMPLATE_PATH("/templates/code-generate/entity.java"),
  CONTROLLER_TEMPLATE_PATH("/templates/code-generate/entity.java"),
  ADD_DTO_TEMPLATE_PATH("/templates/code-generate/add.java.ftl"),
  UPDATE_DTO_TEMPLATE_PATH("/templates/code-generate/update.java.ftl"),
  QUERY_DTO_TEMPLATE_PATH("/templates/code-generate/query.java.ftl"),
  VO_TEMPLATE_PATH("/templates/code-generate/vo.java.ftl");
  private final String value;
}
