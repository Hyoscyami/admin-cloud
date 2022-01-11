package com.xushifei.support.server.service;

import com.xushifei.common.service.BaseService;
import com.xushifei.support.beans.dto.resp.AdministrativeCodeResp;
import com.xushifei.support.beans.dto.req.add.AddAdministrativeCodeReq;
import com.xushifei.support.beans.dto.req.update.UpdateAdministrativeCodeReq;
import com.xushifei.support.beans.dto.req.query.QueryAdministrativeCodeReq;
/**
 * 行政区划编码业务类
 *
 * @author xushifei
 * @since 2022-01-06
 */
public interface AdministrativeCodeService
    extends BaseService<
        AddAdministrativeCodeReq,
        UpdateAdministrativeCodeReq,
        QueryAdministrativeCodeReq,
        AdministrativeCodeResp> {}
