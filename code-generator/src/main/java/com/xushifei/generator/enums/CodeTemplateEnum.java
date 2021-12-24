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
  /** manager模板 */
  MANAGER("%sManager"),
  MANAGER_IMPL("%sManagerImpl"),
  /** 文件路径，包含文件名称 */
  FILE_NAME("%s%s.java"),
  IMPORT_ADD_PATH("%s.dto.req.add.Add%sReq"),
  IMPORT_QUERY_PATH("%s.dto.req.query.Query%sReq"),
  IMPORT_UPDATE_PATH("%s.dto.req.update.Update%sReq"),
  IMPORT_SERVICE_PATH("%s.service.%sService"),
  PACKAGE_SERVICE("%s.service"),
  PACKAGE_ADD_PATH("%s.dto.req.add"),
  PACKAGE_QUERY_PATH("%s.dto.req.query"),
  PACKAGE_UPDATE_PATH("%s.dto.req.update"),
  PACKAGE_VO_PATH("%s.vo"),
  /** 新增DTO类的模板名称 */
  ADD_DTO_CLASS_NAME("Add%sReq"),
  UPDATE_DTO_CLASS_NAME("Update%sReq"),
  QUERY_DTO_CLASS_NAME("Query%sReq"),
  VO_CLASS_NAME("%sVO"),
  SERVICE_CLASS_NAME("%sService"),
  SERVICE_IMPL_CLASS_NAME("%sServiceImpl"),
  /** 生成类文件路径 */
  BASE_OUT_PUT_FILE_PATH("baseOutPutFilePath"),
  VO_FILE_PATH("%s%svo%s"),
  SERVICE_FILE_PATH("%s%sservice%s"),
  SERVICE_IMPL_FILE_PATH("%s%sservice%simpl%s"),
  QUERY_FILE_PATH("%s%sdto%squery%s"),
  ADD_FILE_PATH("%s%sdto%sadd%s"),
  UPDATE_FILE_PATH("%s%sdto%supdate%s"),
  /** 生成模板路径 */
  ENTITY_TEMPLATE_PATH("/templates/code-generate/entity.java"),
  CONTROLLER_TEMPLATE_PATH("/templates/code-generate/controller.java"),
  ADD_DTO_TEMPLATE_PATH("/templates/code-generate/add.java.ftl"),
  UPDATE_DTO_TEMPLATE_PATH("/templates/code-generate/update.java.ftl"),
  QUERY_DTO_TEMPLATE_PATH("/templates/code-generate/query.java.ftl"),
  SERVICE_TEMPLATE_PATH("/templates/code-generate/service.java.ftl"),
  SERVICE_IMPL_TEMPLATE_PATH("/templates/code-generate/service.impl.java.ftl"),
  VO_TEMPLATE_PATH("/templates/code-generate/vo.java.ftl");
  private final String value;
}
