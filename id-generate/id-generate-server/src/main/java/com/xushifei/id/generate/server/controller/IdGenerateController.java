package com.xushifei.id.generate.server.controller;

import com.xushifei.common.dto.ApiResponse;
import com.xushifei.common.utils.ResponseUtils;
import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;
import com.xushifei.id.generate.beans.dto.req.SnowflakeIdReq;
import com.xushifei.id.generate.server.service.IdGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * id生成
 *
 * @author xushifei
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IdGenerateController {
  private final IdGenerateService segmentIdService;
  private final IdGenerateService snowflakeIdService;
  /**
   * 获取单个号段模式id
   *
   * @param req
   * @return
   */
  @PostMapping("/segment/getSegmentId")
  public ApiResponse<Long> getSegmentId(@Valid @RequestBody SegmentIdReq req) {
    return ResponseUtils.success(segmentIdService.getId(req));
  }

  /**
   * 获取单个雪花算法id
   *
   * @param req
   * @return
   */
  @PostMapping("/snowflake/getSnowflakeId")
  public ApiResponse<Long> getSnowflakeId(@Valid @RequestBody SnowflakeIdReq req) {
    return ResponseUtils.success(snowflakeIdService.getId(req));
  }
}
