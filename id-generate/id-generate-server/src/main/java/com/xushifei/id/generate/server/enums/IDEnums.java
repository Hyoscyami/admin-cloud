package com.xushifei.id.generate.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 唯一id常用枚举
 *
 * @author xushifei
 * @date 2022/1/12
 */
@Getter
@AllArgsConstructor
public enum IDEnums {
  /** id服务常用枚举 */
  PATH_KEY("/applications/application", "eureka实例的json的key值"),
  EUREKA_STATUS_UP("up", "eureka上的服务正常运行的状态"),
  /** 雪花算法唯一值 */
  SNOWFLAKE_ID_KEY("%s:%s", "雪花算法hostname:port");
  private final String code;
  private final String msg;
}
