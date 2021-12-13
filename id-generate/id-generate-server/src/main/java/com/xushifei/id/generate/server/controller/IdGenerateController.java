package com.xushifei.id.generate.server.controller;

import com.xushifei.common.model.ApiResponse;
import com.xushifei.common.utils.ResponseUtils;
import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;
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
  /**
   * 号段模式
   *
   * @param req
   * @return
   */
  @PostMapping("/segment/getSegmentId")
  public ApiResponse<Long> getSegmentId(@Valid @RequestBody SegmentIdReq req) {
    return ResponseUtils.success(segmentIdService.getId(req));
  }
}
