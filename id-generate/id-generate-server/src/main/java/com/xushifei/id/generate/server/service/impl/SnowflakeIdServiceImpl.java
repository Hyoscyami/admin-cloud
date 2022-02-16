package com.xushifei.id.generate.server.service.impl;

import com.xushifei.core.cache.manager.CacheManager;
import com.xushifei.id.generate.beans.dto.req.SnowflakeIdReq;
import com.xushifei.id.generate.server.entity.SnowflakeAlloc;
import com.xushifei.id.generate.server.enums.IDEnums;
import com.xushifei.id.generate.server.manager.SnowflakeAllocManager;
import com.xushifei.id.generate.server.service.IdGenerateService;
import com.xushifei.id.generate.server.utils.SnowflakeIdWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 雪花算法id生成
 *
 * @author xushifei
 * @date 2021/12/13
 */
@Slf4j
@Service("snowflakeIdService")
@RequiredArgsConstructor
public class SnowflakeIdServiceImpl implements IdGenerateService<SnowflakeIdReq> {
  /** id生成工具容器 */
  private final Map<String, SnowflakeIdWorker> context = new ConcurrentHashMap<>();

  private final SnowflakeAllocManager snowflakeAllocManager;
  private final CacheManager cacheManager;
  /**
   * 获取单个唯一id
   *
   * @param req
   * @return
   */
  @Override
  public Long getId(SnowflakeIdReq req) {
    // 获取id时预处理，本地缓存没有缓存id生成工具类时，进行缓存操作
    this.preHandle(req);
    SnowflakeIdWorker idWorker = this.context.get(req.getServerName());
    if (Objects.nonNull(idWorker)) {
      return idWorker.nextId();
    }
    // 本地缓存没有，尝试从redis中获取服务的workerId
    log.info("本地缓存没有，尝试从redis中获取服务的workerId,req:{}", req);
    String workerIdKey =
        String.format(IDEnums.SNOWFLAKE_WORKER_ID_KEY.getCode(), req.getServerName());
    String key = IDEnums.ALLOC_SNOWFLAKE_ID_KEY.getCode();
    try {
      boolean isLock = cacheManager.tryLock(key, TimeUnit.SECONDS, 2, 3);
      if (!isLock) {
        log.info("该节点没有争抢到生成workerId的分布式锁");
      }
    } catch (Exception e) {
      log.error("尝试生成雪花算法id异常:", e);
    } finally {
      cacheManager.unlock(key);
    }
    // 无记录，redis分布式锁，并生成一条hostname、serverPort与
    return idWorker.nextId();
  }

  /**
   * 批量获取唯一id
   *
   * @param req
   * @return
   */
  @Override
  public List<Long> listId(SnowflakeIdReq req) {
    return null;
  }

  /**
   * 获取id时预处理，本地缓存没有缓存id生成工具类时，进行缓存操作
   *
   * @param req
   */
  private void preHandle(SnowflakeIdReq req) {
    SnowflakeIdWorker idWorker = this.context.get(req.getServerName());
    if (Objects.isNull(idWorker)) {
      // 本地缓存没有该服务名的记录，去数据库查
      SnowflakeAlloc snowflakeAlloc =
          snowflakeAllocManager
              .lambdaQuery()
              .eq(SnowflakeAlloc::getServerName, req.getServerName())
              .one();
      idWorker = new SnowflakeIdWorker(snowflakeAlloc.getWorkerId());
      this.context.put(req.getServerName(), idWorker);
    }
  }
}
