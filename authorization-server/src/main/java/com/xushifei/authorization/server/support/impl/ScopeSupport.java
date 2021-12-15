package com.xushifei.authorization.server.support.impl;

import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.mapper.ScopeMapper;
import com.xushifei.authorization.server.support.IScopeSupport;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeSupport extends ServiceImpl<ScopeMapper, Scope> implements IScopeSupport {

}
