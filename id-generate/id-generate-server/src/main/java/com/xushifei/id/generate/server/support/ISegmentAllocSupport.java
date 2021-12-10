package com.xushifei.id.generate.server.support;

import com.xushifei.id.generate.server.entity.SegmentAlloc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 号段模式id分配表 服务类
 *
 * @author xushifei
 * @since 2021-12-10
 */
public interface ISegmentAllocSupport extends IService<SegmentAlloc> {
  /**
   * 更新最大id并返回当前业务标识对应的号段数据
   *
   * @param segmentAlloc
   * @return
   */
  SegmentAlloc updateMaxIdAndGetLeafAlloc(SegmentAlloc segmentAlloc);
}
