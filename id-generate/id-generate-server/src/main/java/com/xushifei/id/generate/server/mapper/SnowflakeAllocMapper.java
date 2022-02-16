package com.xushifei.id.generate.server.mapper;

import com.xushifei.id.generate.server.entity.SnowflakeAlloc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 雪花算法分配id Mapper 接口
 *
 * @author xushifei
 * @since 2022-01-12
 */
public interface SnowflakeAllocMapper extends BaseMapper<SnowflakeAlloc> {
  /**
   * 获取最大的workerId
   *
   * @return
   */
  Long getMaxWorkerId();
}
