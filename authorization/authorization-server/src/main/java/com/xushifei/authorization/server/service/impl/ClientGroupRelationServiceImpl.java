package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.entity.ClientGroupRelation;
import com.xushifei.authorization.server.manager.ClientGroupRelationManager;
import com.xushifei.authorization.server.service.ClientGroupRelationService;
import com.xushifei.authorization.server.vo.ClientGroupRelationVO;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 客户端分组关联业务类
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientGroupRelationServiceImpl extends BaseServiceImpl<ClientGroupRelationManager, ClientGroupRelation>
    implements ClientGroupRelationService {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public ClientGroupRelation convertAddReqToEntity(BaseAddReq req) {
    ClientGroupRelation client = new ClientGroupRelation();
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
  protected ClientGroupRelation convertUpdateReqToEntity(BaseUpdateReq req) {
    ClientGroupRelation client = new ClientGroupRelation();
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
  protected BaseVO convertEntityToVO(ClientGroupRelation entity) {
    ClientGroupRelationVO vo = new ClientGroupRelationVO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(ClientGroupRelation entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(ClientGroupRelation entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(ClientGroupRelation entity) {
    entity.assignModifyInfo();
  }
}
