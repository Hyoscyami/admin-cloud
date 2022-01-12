package com.xushifei.id.generate.server.manager.impl;

import com.xushifei.id.generate.server.entity.SnowflakeAlloc;
import com.xushifei.id.generate.server.mapper.SnowflakeAllocMapper;
import com.xushifei.id.generate.server.manager.SnowflakeAllocManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雪花算法分配id 服务实现类
 * </p>
 *
 * @author xushifei
 * @since 2022-01-12
 */
@Service
public class SnowflakeAllocManagerImpl extends ServiceImpl<SnowflakeAllocMapper, SnowflakeAlloc> implements SnowflakeAllocManager {

}
