package com.xushifei.authorization.server.service.impl;

import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.manager.ScopeManager;
import com.xushifei.authorization.server.service.ScopeService;
import com.xushifei.authorization.server.vo.ScopeVO;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 权限业务类
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScopeServiceImpl extends BaseServiceImpl<ScopeManager, Scope>
    implements ScopeService {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public Scope convertAddReqToEntity(BaseAddReq req) {
    Scope client = new Scope();
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
  protected Scope convertUpdateReqToEntity(BaseUpdateReq req) {
    Scope client = new Scope();
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
  protected BaseVO convertEntityToVO(Scope entity) {
    ScopeVO vo = new ScopeVO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(Scope entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(Scope entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(Scope entity) {
    entity.assignModifyInfo();
  }
}
