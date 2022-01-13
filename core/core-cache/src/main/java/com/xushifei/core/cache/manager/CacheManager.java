package com.xushifei.core.cache.manager;

import java.util.concurrent.TimeUnit;

/**
 * 缓存常用操作
 *
 * @author xushifei
 * @date 2022/1/7
 */
public interface CacheManager {
  /**
   * set操作，支持过期时间设置
   *
   * @param key
   * @param value
   * @param timeout
   * @param timeUnit
   */
  void set(String key, Object value, long timeout, TimeUnit timeUnit);

  /**
   * set操作，不过期
   *
   * @param key
   * @param value
   */
  void set(String key, Object value);

  /**
   * get操作
   *
   * @param key
   * @param <T>
   * @return
   */
  <T> T get(String key);

  /**
   * 删除key
   *
   * @param key
   * @return
   */
  boolean delete(String key);

  /**
   * 分布式锁
   *
   * @param lockKey
   * @param unit
   * @param waitTime
   * @param leaseTime
   * @return
   */
  boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

  /**
   * 解锁
   *
   * @param lockKey
   */
  void unlock(String lockKey);
}
