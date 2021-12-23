package com.xushifei.generator.dto;

import lombok.Data;

/**
 * controller生成
 *
 * @author xushifei
 * @date 2021/12/22
 */
@Data
public class ControllerTemplateDTO {
  /** controller注入的service完成名称，import使用 */
  private String serviceClassCompleteName;
  /** controller注入的service类名，如UserService */
  private String serviceClassName;
  /** controller注入的service类名变量，如userService */
  private String serviceName;
  /** 新增请求名，完整路径，import使用 */
  private String addReqCompleteName;
  /** 新增请求名，入参使用 */
  private String addReqSimpleName;
  /** 更新请求名，完整路径，import使用 */
  private String updateReqCompleteName;
  /** 更新请求名，入参使用 */
  private String updateReqSimpleName;
  /** 查询请求名，完整路径，import使用 */
  private String queryReqCompleteName;
  /** 查询请求，入参使用 */
  private String queryReqSimpleName;
  /** 返回工具类完整路径，import使用 */
  private String responseUtilCompleteName;
  /** 基础响应类完整路径,import使用 */
  private String responseCompleteName;
  /** 基础VO完整路径，import使用 */
  private String baseVOCompleteName;
}
