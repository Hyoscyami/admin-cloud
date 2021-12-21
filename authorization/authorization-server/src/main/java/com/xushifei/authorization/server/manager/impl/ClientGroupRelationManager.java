package com.xushifei.authorization.server.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xushifei.authorization.server.entity.ClientGroupRelation;
import com.xushifei.authorization.server.manager.IClientGroupRelationManager;
import com.xushifei.authorization.server.mapper.ClientGroupRelationMapper;
import org.springframework.stereotype.Service;

/**
 * 客户端分组关联 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ClientGroupRelationManager
    extends ServiceImpl<ClientGroupRelationMapper, ClientGroupRelation>
    implements IClientGroupRelationManager {}
