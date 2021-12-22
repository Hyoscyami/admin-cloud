package com.xushifei.generator.dto;

import lombok.Data;

import java.util.List;

/**
 * 增删改查请求代码生成模板
 *
 * @author xushifei
 * @since 2021/12/16
 */
@Data
public class BaseCodeTemplateDTO {
  /** 父类完整名称，如com.xushifei.generator.dto.CodeTemplateDTO */
  private String superClassCompleteName;
  /** 父类名称 */
  private String superClassSimpleName;
  /** 类名 */
  private String className;
  /** 忽略字段 */
  private List<String> ignoreColumns;
}
