package com.xushifei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xusf
 * @date 2020/9/20 上午5:25
 * @description 所有接口通用返回码枚举类
 */
@Getter
@AllArgsConstructor
public enum ApiCodeEnum {
  /** 相关枚举 */
  SUCCESS("00000", "成功"),
  PARAM_ERROR("A0400", "请求参数错误"),
  USER_AUTHENTICATION_ERROR("A0220", "用户身份校验失败"),
  UNAUTHORIZED_ACCESS("A0301", "访问未授权"),
  VALID_CODE_ERROR("A0240", "验证码错误"),
  USER_AUTHENTICATION_EXPIRED("A0230", "用户登录已过期"),
  SYSTEM_ERROR("B0001", "系统错误");
  /** 返回码 */
  private final String code;
  /** 返回描述 */
  private final String msg;
}
