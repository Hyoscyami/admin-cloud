package com.xushifei.id.generate.server.manager;

import com.xushifei.id.generate.server.entity.SnowflakeAlloc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 雪花算法分配id 服务类
 *
 * @author xushifei
 * @since 2022-01-12
 */
public interface SnowflakeAllocManager extends IService<SnowflakeAlloc> {
  /**
   * 获取系统中能使用的最大workerId
   *
   * @return
   */
  Long getMaxWorkerId();
}
