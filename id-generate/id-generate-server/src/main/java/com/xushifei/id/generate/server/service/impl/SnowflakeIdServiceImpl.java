package com.xushifei.id.generate.server.service.impl;

import com.google.common.collect.Maps;
import com.xushifei.id.generate.beans.dto.req.SnowflakeIdReq;
import com.xushifei.id.generate.server.service.IdGenerateService;
import com.xushifei.id.generate.server.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 雪花算法id生成
 *
 * @author xushifei
 * @date 2021/12/13
 */
@Service("snowflakeIdService")
public class SnowflakeIdServiceImpl implements IdGenerateService<SnowflakeIdReq> {
  /** id生成工具容器 */
  private Map<String, SnowflakeIdWorker> CONTEXT = new ConcurrentHashMap<>();

  private final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
  /**
   * 获取单个唯一id
   *
   * @param req
   * @return
   */
  @Override
  public Long getId(SnowflakeIdReq req) {
    // 根据hostname和serverPort查询本地缓存是否有记录
    // 有记录，取出并生成id
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
}
