package com.xushifei.id.generate.server.service.impl;

import com.xushifei.id.generate.beans.dto.req.BaseIdAllocReq;
import com.xushifei.id.generate.server.service.IdGenerateService;
import com.xushifei.id.generate.server.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 雪花算法id生成
 *
 * @author xushifei
 * @date 2021/12/13
 */
@Service("snowflakeIdService")
public class SnowflakeIdServiceImpl implements IdGenerateService {
  private final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
  /**
   * 获取单个唯一id
   *
   * @param req
   * @return
   */
  @Override
  public Long getId(BaseIdAllocReq req) {
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
  public List<Long> listId(BaseIdAllocReq req) {
    return null;
  }
}
