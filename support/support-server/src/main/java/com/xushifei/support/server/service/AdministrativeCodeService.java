package com.xushifei.support.server.service;

import com.xushifei.common.service.BaseService;
import com.xushifei.support.server.vo.AdministrativeCodeVO;
import com.xushifei.support.server.dto.add.AddAdministrativeCodeReq;
import com.xushifei.support.server.dto.update.UpdateAdministrativeCodeReq;
import com.xushifei.support.server.dto.query.QueryAdministrativeCodeReq;
/**
 * 行政区划编码业务类
 *
 * @author xushifei
 * @since 2022-01-06
 */
public interface AdministrativeCodeService extends BaseService<AddAdministrativeCodeReq, UpdateAdministrativeCodeReq, QueryAdministrativeCodeReq, AdministrativeCodeVO> {

}