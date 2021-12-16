package com.xushifei.generator.dto;

import lombok.Data;

import java.util.List;

/**
 * 新增DTO模板
 *
 * @author xushifei
 * @since 2021/12/16
 */
@Data
public class AddTemplateDto {
  /** 类名 */
  private String className;
  /** 忽略字段 */
  private List<String> ignoreColumns;
}
