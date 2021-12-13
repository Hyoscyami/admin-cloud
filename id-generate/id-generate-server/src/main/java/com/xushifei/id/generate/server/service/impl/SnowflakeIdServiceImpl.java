package com.xushifei.id.generate.server.service.impl;

import com.xushifei.id.generate.beans.dto.req.BaseIdAllocReq;
import com.xushifei.id.generate.server.service.IdGenerateService;
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
  /**
   * 获取单个唯一id
   *
   * @param req
   * @return
   */
  @Override
  public Long getId(BaseIdAllocReq req) {
    return 1L;
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
