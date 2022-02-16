package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.dto.req.add.AddClientReq;
import com.xushifei.authorization.server.dto.req.query.QueryClientReq;
import com.xushifei.authorization.server.dto.req.update.UpdateClientReq;
import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.entity.ClientScopeRelation;
import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.manager.ClientManager;
import com.xushifei.authorization.server.manager.ClientScopeRelationManager;
import com.xushifei.authorization.server.manager.ScopeManager;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.vo.ClientVO;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.util.AssertUtils;
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
public class ClientServiceImpl
    extends BaseServiceImpl<
        ClientManager, AddClientReq, UpdateClientReq, QueryClientReq, ClientVO, Client>
    implements ClientService {
  private final ClientScopeRelationManager clientScopeRelationManager;
  private final ScopeManager scopeManager;

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
      return scopeManager.list();
    }
    // 根据客户端权限关联表查询客户端权限列表
    List<Long> scopeIds =
        clientScopeRelationManager
            .lambdaQuery()
            .eq(ClientScopeRelation::getClientId, client)
            .list()
            .stream()
            .map(ClientScopeRelation::getScopeId)
            .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(scopeIds)) {
      return Collections.emptyList();
    }
    return scopeManager.listByIds(scopeIds);
  }

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public Client convertAddReqToEntity(AddClientReq req) {
    Client client = new Client();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * 更新请求转实体
   *
   * @param req
   * @return
   */
  @Override
  protected Client convertUpdateReqToEntity(UpdateClientReq req) {
    Client entity = new Client();
    BeanUtils.copyProperties(req, entity);
    return entity;
  }

  /**
   * 实体转vo
   *
   * @param entity
   * @return
   */
  @Override
  protected ClientVO convertEntityToVO(Client entity) {
    ClientVO vo = new ClientVO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  public void preSave(Client entity) {
    super.preSave(entity);
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(Client entity) {
    super.preRemove(entity);
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(Client entity) {
    super.preUpdate(entity);
  }
}
