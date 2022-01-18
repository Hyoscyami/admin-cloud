package com.xushifei.core.doc.dto;

import lombok.Data;

/**
 * openapi数据类型
 *
 * @author xushifei
 * @date 2022/1/18
 */
@Data
public class DataType {
  /** 类型 */
  private String type;
  /** 格式 */
  private String format;
  /** 注释 */
  private String comments;
}
