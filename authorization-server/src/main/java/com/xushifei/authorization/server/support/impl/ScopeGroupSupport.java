package com.xushifei.authorization.server.support.impl;

import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.mapper.ScopeGroupMapper;
import com.xushifei.authorization.server.support.IScopeGroupSupport;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限分组 服务实现类
 * </p>
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeGroupSupport extends ServiceImpl<ScopeGroupMapper, ScopeGroup> implements IScopeGroupSupport {

}
