package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.manager.ClientManager;
import com.xushifei.authorization.server.manager.ScopeManager;
import com.xushifei.authorization.server.manager.impl.ScopeGroupManagerImpl;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.vo.ClientVO;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.utils.AssertUtils;
import com.xushifei.common.vo.BaseVO;
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
  private final ScopeGroupManagerImpl scopeGroupSupport;
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
    List<ScopeGroup> scopeGroups = scopeGroupSupport.listByClientId(clientId);
    if (CollectionUtils.isEmpty(scopeGroups)) {
      return Collections.emptyList();
    }
    return scopeManager.listByGroupIds(
        scopeGroups.stream().map(ScopeGroup::getId).collect(Collectors.toList()));
  }

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public Client convertAddReqToEntity(BaseAddReq req) {
    Client client = new Client();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * 更新请求转entity
   *
   * @param req
   * @return
   */
  @Override
  protected Client convertUpdateReqToEntity(BaseUpdateReq req) {
    Client client = new Client();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * entity转VO
   *
   * @param entity
   * @return
   */
  @Override
  protected BaseVO convertEntityToVO(Client entity) {
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
  protected void preSave(Client entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(Client entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(Client entity) {
    entity.assignModifyInfo();
  }
}
