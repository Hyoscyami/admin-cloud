package com.xushifei.core.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置对应实体类
 *
 * @author xushifei
 * @date 2021/4/8
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
  /**
   * SINGLE_MODE("single","单例模式"), SENTINEL_MODE("sentinel","哨兵模式"), CLUSTER_MODE("cluster","集群模式")
   *
   * @see com.xushifei.core.cache.enums.CacheEnum#SINGLE_MODE
   */
  private String mode;
  /** 连接超时，单位：毫秒 默认值：10000 同任何节点建立连接时的等待超时。时间单位是毫秒 */
  private Integer connectTimeout;
  /** 命令等待超时，单位：毫秒 默认值：3000 等待节点回复命令的时间。该时间从命令发送成功时开始计时 */
  private Integer timeout;
  /** 密码 默认值：null 用于节点身份验证的密码 */
  private String password;
  /** 客户端名称 默认值：null 在Redis节点里显示的客户端名称 */
  private String clientName;
  /** 主节点连接池大小 默认值：64 多主节点的环境里，每个 主节点的连接池最大容量。连接池的连接数量自动弹性伸缩 */
  private Integer masterConnectionPoolSize;
  /** 主节点最小空闲连接数 默认值：32 多节点的环境里，每个 主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度 */
  private Integer masterConnectionMinimumIdleSize;
  /**
   * 连接空闲超时，单位：毫秒 默认值：10000 如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒
   */
  private Integer idleConnectionTimeout;
  /** 重新连接时间间隔 */
  private Integer reconnectionTimeout;
  /** 从节点连接池大小 默认值：64 多从节点的环境里，每个 从服务节点里用于普通操作（非 发布和订阅）连接的连接池最大容量。连接池的连接数量自动弹性伸缩 */
  private Integer slaveConnectionPoolSize;
  /** 从节点最小空闲连接数 默认值：32 多从节点的环境里，每个 从服务节点里用于普通操作（非 发布和订阅）的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时读取反映速度 */
  private Integer slaveConnectionMinimumIdleSize;
  /** 哨兵节点地址 */
  private String[] sentinelAddresses;
  /** 数据库编号 默认值：0 尝试连接的数据库编号 */
  private Integer database;
  /** 主服务器的名称 主服务器的名称是哨兵进程中用来监测主从服务切换情况的 */
  private String masterName;
  /** redis连接地址 格式：redis://ip:port */
  private String address;
}
