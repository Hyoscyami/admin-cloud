package com.xushifei.id.generate.client;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.dto.ApiResponse;
import com.xushifei.common.utils.ResponseUtils;
import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;
import com.xushifei.id.generate.beans.dto.req.SnowflakeIdReq;

import java.util.List;

/**
 * 熔断
 *
 * @author xushifei
 * @since 2021/12/10
 */
public class IdClientFallBack implements IdClient {
  /**
   * 号段模式
   *
   * @param req
   * @return
   */
  @Override
  public ApiResponse<Long> getSegmentId(SegmentIdReq req) {
    return ResponseUtils.fail(ApiCodeEnum.SYSTEM_ERROR);
  }

  /**
   * 批量获取号段模式唯一id
   *
   * @param req
   * @return
   */
  @Override
  public ApiResponse<List<Long>> listSegmentIds(SegmentIdReq req) {
    return ResponseUtils.fail(ApiCodeEnum.SYSTEM_ERROR);
  }

  /**
   * 雪花算法
   *
   * @return
   */
  @Override
  public ApiResponse<Long> getSnowflakeId(SnowflakeIdReq req) {
    return ResponseUtils.fail(ApiCodeEnum.SYSTEM_ERROR);
  }

  /**
   * 批量获取雪花算法id
   *
   * @param req
   * @return
   */
  @Override
  public ApiResponse<List<Long>> listSnowflakeIds(SnowflakeIdReq req) {
    return ResponseUtils.fail(ApiCodeEnum.SYSTEM_ERROR);
  }
}
