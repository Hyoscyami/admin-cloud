package com.xushifei.authorization.server.manager.impl;

import com.xushifei.authorization.server.entity.ScopeGroupRelation;
import com.xushifei.authorization.server.manager.ScopeGroupRelationManager;
import com.xushifei.authorization.server.mapper.ScopeGroupRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 权限分组关联 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeGroupRelationManagerImpl
    extends ServiceImpl<ScopeGroupRelationMapper, ScopeGroupRelation>
    implements ScopeGroupRelationManager {}