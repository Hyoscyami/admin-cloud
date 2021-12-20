package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.dto.add.AddClientReq;
import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.manager.impl.ClientManager;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.manager.IClientManager;
import com.xushifei.authorization.server.manager.ScopeSupport;
import com.xushifei.authorization.server.manager.impl.ScopeGroupSupport;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户端业务类
 *
 * @author xushifei
 * @since 2021/12/15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends BaseServiceImpl<ClientManager, Client>
    implements ClientService {
  private final ScopeGroupSupport scopeGroupSupport;
  private final ScopeSupport scopeSupport;

  /**
   * 根据客户端ID查询权限列表
   *
   * @param clientId
   * @return
   */
  @Override
  public List<Scope> listScopesByClientId(Long clientId) {
    AssertUtils.notNull(clientId, "clientId不能为空");
    Client client = this.manager.getById(clientId);
    AssertUtils.notNull(client, "clientId不存在");
    // 超管客户端，返回全部权限
    if (client.getAdmin()) {
      return scopeSupport.list();
    }
    List<ScopeGroup> scopeGroups = scopeGroupSupport.listByClientId(clientId);
    if (CollectionUtils.isEmpty(scopeGroups)) {
      return Collections.emptyList();
    }
    return scopeSupport.listByGroupIds(
        scopeGroups.stream().map(ScopeGroup::getId).collect(Collectors.toList()));
  }

  @Override
  protected Client convertAddReqToEntity(BaseAddReq req) {
    AddClientReq addReq = (AddClientReq) req;
    Client client = new Client();
    BeanUtils.copyProperties(addReq, client);
    return client;
  }
}
