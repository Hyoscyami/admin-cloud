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
  ALLOC_SNOWFLAKE_ID_KEY("id:generate:snowflake:workerId", "分配雪花算法数据时默认的redis分布式锁的key"),
  PATH_KEY("/applications/application", "eureka实例的json的key值"),
  EUREKA_STATUS_UP("up", "eureka上的服务正常运行的状态"),
  /** 雪花算法唯一值 */
  SNOWFLAKE_ID_KEY("%s:%s", "雪花算法hostname:port");
  private final String code;
  private final String msg;
}
