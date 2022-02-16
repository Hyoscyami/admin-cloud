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
  /** 雪花算法 */
  SNOWFLAKE_WORKER_ID_KEY("snowflake:id:key:%s", "雪花算法redis中存储服务workerId的key");
  private final String code;
  private final String msg;
}
