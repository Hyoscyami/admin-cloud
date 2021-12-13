package com.xushifei.common.model;

import lombok.Data;

/**
 * @author xusf
 * @date 2020/9/19 上午7:12
 * @description 所有系统共用的返回码类
 */
@Data
public class ApiResponse<T> {
  /** 返回码 */
  private String code;
  /** 描述 */
  private String msg;
  /** 返回数据 */
  private T data;
}
