package com.xushifei.authorization.server.support.impl;

import com.xushifei.authorization.server.entity.ScopeGroupRelation;
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
public class ScopeGroupRelationSupport
    extends ServiceImpl<ScopeGroupRelationMapper, ScopeGroupRelation>
    implements com.xushifei.authorization.server.support.ScopeGroupRelationSupport {}
