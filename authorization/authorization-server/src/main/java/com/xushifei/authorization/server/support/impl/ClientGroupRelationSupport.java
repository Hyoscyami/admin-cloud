package com.xushifei.authorization.server.support.impl;

import com.xushifei.authorization.server.entity.ClientGroupRelation;
import com.xushifei.authorization.server.mapper.ClientGroupRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户端分组关联 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ClientGroupRelationSupport
    extends ServiceImpl<ClientGroupRelationMapper, ClientGroupRelation>
    implements com.xushifei.authorization.server.support.ClientGroupRelationSupport {}
