package com.xushifei.id.generate.server.mapper;

import com.xushifei.id.generate.server.entity.SegmentAlloc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 号段模式id分配表 Mapper 接口
 *
 * @author xushifei
 * @since 2021-12-10
 */
public interface SegmentAllocMapper extends BaseMapper<SegmentAlloc> {
  /**
   * 根据业务标识更新最大步长
   *
   * @param segmentAlloc
   */
  void updateMaxIdByBizTag(SegmentAlloc segmentAlloc);
}
