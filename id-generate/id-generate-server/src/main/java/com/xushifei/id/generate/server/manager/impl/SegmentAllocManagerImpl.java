package com.xushifei.id.generate.server.manager.impl;

import com.xushifei.id.generate.server.entity.SegmentAlloc;
import com.xushifei.id.generate.server.mapper.SegmentAllocMapper;
import com.xushifei.id.generate.server.manager.SegmentAllocManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 号段模式id分配表 服务实现类
 *
 * @author xushifei
 * @since 2021-12-10
 */
@Service
public class SegmentAllocManagerImpl extends ServiceImpl<SegmentAllocMapper, SegmentAlloc>
    implements SegmentAllocManager {
  /**
   * 更新最大id并返回当前业务标识对应的号段数据
   *
   * @param segmentAlloc
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public SegmentAlloc updateMaxIdAndGetLeafAlloc(SegmentAlloc segmentAlloc) {
    this.baseMapper.updateMaxIdByBizTag(segmentAlloc);
    return this.lambdaQuery().eq(SegmentAlloc::getBizTag, segmentAlloc.getBizTag()).one();
  }
}
