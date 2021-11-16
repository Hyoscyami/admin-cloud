package com.xushifei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author xushifei
 * @date 2021/11/16
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
  /** 通用状态 */
  STATUS_ENABLE(1, "启用"),
  STATUS_DISABLE(2, "禁用");
  private final int value;
  private final String msg;
}
