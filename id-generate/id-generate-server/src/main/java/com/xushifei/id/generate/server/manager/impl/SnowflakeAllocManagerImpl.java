package com.xushifei.id.generate.server.manager.impl;

import com.xushifei.id.generate.server.entity.SnowflakeAlloc;
import com.xushifei.id.generate.server.mapper.SnowflakeAllocMapper;
import com.xushifei.id.generate.server.manager.SnowflakeAllocManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 雪花算法分配id 服务实现类
 *
 * @author xushifei
 * @since 2022-01-12
 */
@Service
public class SnowflakeAllocManagerImpl extends ServiceImpl<SnowflakeAllocMapper, SnowflakeAlloc>
    implements SnowflakeAllocManager {
  /**
   * 获取系统中能使用的最大workerId
   *
   * @return
   */
  @Override
  public Long getMaxWorkerId() {
    return this.baseMapper.getMaxWorkerId();
  }
}
