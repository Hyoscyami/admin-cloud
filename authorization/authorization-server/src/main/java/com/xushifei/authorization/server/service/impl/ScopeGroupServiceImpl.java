package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.manager.ScopeGroupManager;
import com.xushifei.authorization.server.service.ScopeGroupService;
import com.xushifei.authorization.server.vo.ScopeGroupVO;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 权限分组业务类
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScopeGroupServiceImpl extends BaseServiceImpl<ScopeGroupManager, ScopeGroup>
    implements ScopeGroupService {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public ScopeGroup convertAddReqToEntity(BaseAddReq req) {
    ScopeGroup client = new ScopeGroup();
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
  protected ScopeGroup convertUpdateReqToEntity(BaseUpdateReq req) {
    ScopeGroup client = new ScopeGroup();
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
  protected BaseVO convertEntityToVO(ScopeGroup entity) {
    ScopeGroupVO vo = new ScopeGroupVO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(ScopeGroup entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(ScopeGroup entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(ScopeGroup entity) {
    entity.assignModifyInfo();
  }
}
