package com.xushifei.authorization.server.manager.impl;

import com.xushifei.authorization.server.entity.ClientScopeRelation;
import com.xushifei.authorization.server.mapper.ClientScopeRelationMapper;
import com.xushifei.authorization.server.manager.ClientScopeRelationManager;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端权限关联 服务实现类
 * </p>
 *
 * @author xushifei
 * @since 2022-01-05
 */
@Service
public class ClientScopeRelationManagerImpl extends ServiceImpl<ClientScopeRelationMapper, ClientScopeRelation> implements ClientScopeRelationManager {

}
