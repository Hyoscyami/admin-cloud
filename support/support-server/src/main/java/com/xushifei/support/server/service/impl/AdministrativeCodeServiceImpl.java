package com.xushifei.support.server.service.impl;

import com.xushifei.support.server.entity.AdministrativeCode;
import com.xushifei.support.server.manager.AdministrativeCodeManager;
import com.xushifei.support.server.service.AdministrativeCodeService;
import com.xushifei.support.beans.dto.resp.AdministrativeCodeResp;
import com.xushifei.support.beans.dto.req.add.AddAdministrativeCodeReq;
import com.xushifei.support.beans.dto.req.update.UpdateAdministrativeCodeReq;
import com.xushifei.support.beans.dto.req.query.QueryAdministrativeCodeReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 行政区划编码业务类
 *
 * @author xushifei
 * @since 2022-01-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdministrativeCodeServiceImpl
    extends BaseServiceImpl<
        AdministrativeCodeManager,
        AddAdministrativeCodeReq,
        UpdateAdministrativeCodeReq,
        QueryAdministrativeCodeReq,
        AdministrativeCodeResp,
        AdministrativeCode>
    implements AdministrativeCodeService {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public AdministrativeCode convertAddReqToEntity(AddAdministrativeCodeReq req) {
    AdministrativeCode entity = new AdministrativeCode();
    BeanUtils.copyProperties(req, entity);
    return entity;
  }

  /**
   * 更新请求转entity
   *
   * @param req
   * @return
   */
  @Override
  protected AdministrativeCode convertUpdateReqToEntity(UpdateAdministrativeCodeReq req) {
    AdministrativeCode entity = new AdministrativeCode();
    BeanUtils.copyProperties(req, entity);
    return entity;
  }

  /**
   * entity转VO
   *
   * @param entity
   * @return
   */
  @Override
  protected AdministrativeCodeResp convertEntityToVO(AdministrativeCode entity) {
    AdministrativeCodeResp vo = new AdministrativeCodeResp();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(AdministrativeCode entity) {
    super.preSave(entity);
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(AdministrativeCode entity) {
    super.preRemove(entity);
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(AdministrativeCode entity) {
    super.preUpdate(entity);
  }
}
