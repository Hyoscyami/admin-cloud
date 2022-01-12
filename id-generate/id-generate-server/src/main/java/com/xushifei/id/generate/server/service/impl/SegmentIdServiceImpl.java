package com.xushifei.id.generate.server.service.impl;

import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;
import com.xushifei.id.generate.server.service.IdGenerateService;
import com.xushifei.id.generate.server.manager.SegmentAllocManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 号段模式
 *
 * @author xushifei
 * @date 2021/12/13
 */
@Service("segmentIdService")
@RequiredArgsConstructor
public class SegmentIdServiceImpl implements IdGenerateService<SegmentIdReq> {
  private final SegmentAllocManager segmentAllocSupport;
  /**
   * 获取单个唯一id
   *
   * @param req
   * @return
   */
  @Override
  public Long getId(SegmentIdReq req) {
    return 1L;
  }

  /**
   * 批量获取唯一id
   *
   * @param req
   * @return
   */
  @Override
  public List<Long> listId(SegmentIdReq req) {
    return null;
  }
}
