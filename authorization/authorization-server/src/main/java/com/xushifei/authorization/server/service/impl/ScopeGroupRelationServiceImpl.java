package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.entity.ScopeGroupRelation;
import com.xushifei.authorization.server.manager.ScopeGroupRelationManager;
import com.xushifei.authorization.server.service.ScopeGroupRelationService;
import com.xushifei.authorization.server.vo.ScopeGroupRelationVO;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 权限分组关联业务类
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScopeGroupRelationServiceImpl extends BaseServiceImpl<ScopeGroupRelationManager, ScopeGroupRelation>
    implements ScopeGroupRelationService {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public ScopeGroupRelation convertAddReqToEntity(BaseAddReq req) {
    ScopeGroupRelation client = new ScopeGroupRelation();
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
  protected ScopeGroupRelation convertUpdateReqToEntity(BaseUpdateReq req) {
    ScopeGroupRelation client = new ScopeGroupRelation();
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
  protected BaseVO convertEntityToVO(ScopeGroupRelation entity) {
    ScopeGroupRelationVO vo = new ScopeGroupRelationVO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(ScopeGroupRelation entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(ScopeGroupRelation entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(ScopeGroupRelation entity) {
    entity.assignModifyInfo();
  }
}
