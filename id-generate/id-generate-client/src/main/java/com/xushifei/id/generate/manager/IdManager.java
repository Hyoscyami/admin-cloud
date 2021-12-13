package com.xushifei.id.generate.manager;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import com.xushifei.common.model.ApiResponse;
import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;
import com.xushifei.id.generate.beans.dto.req.SnowflakeIdReq;
import com.xushifei.id.generate.client.IdClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * id 封装层
 *
 * @author xushifei
 * @since 2021/12/13
 */
@Service
@RequiredArgsConstructor
public class IdManager {
  private final IdClient idClient;
  /**
   * 获取号段模式ID
   *
   * @param req
   * @return
   */
  public Long getSegmentId(SegmentIdReq req) {
    ApiResponse<Long> response = idClient.getSegmentId(req);
    if (!Objects.equals(ApiCodeEnum.SUCCESS.getCode(), response.getCode())) {
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "获取号段模式ID失败");
    }
    return response.getData();
  }

  /**
   * 获取雪花算法ID
   *
   * @param req
   * @return
   */
  public Long getSnowflakeId(SnowflakeIdReq req) {
    ApiResponse<Long> response = idClient.getSnowflakeId(req);
    if (!Objects.equals(ApiCodeEnum.SUCCESS.getCode(), response.getCode())) {
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "获取号段模式ID失败");
    }
    return response.getData();
  }
}
