package com.xushifei.authorization.server.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.manager.ScopeManager;
import com.xushifei.authorization.server.mapper.ScopeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeManagerImpl extends ServiceImpl<ScopeMapper, Scope> implements ScopeManager {}
