package com.xushifei.authorization.server.service;

import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.common.service.BaseService;

import java.util.List;

/**
 * 客户端业务类
 *
 * @author xushifei
 * @since 2021/12/15
 */
public interface ClientService extends BaseService<Client> {
  /**
   * 根据客户端ID查询权限列表
   *
   * @param clientId
   * @return
   */
  List<Scope> listScopesByClientId(Long clientId);
}
