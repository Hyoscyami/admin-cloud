package com.xushifei.support.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xushifei.support.server.service.AdministrativeCodeService;
import com.xushifei.support.beans.dto.req.add.AddAdministrativeCodeReq;
import com.xushifei.support.beans.dto.req.update.UpdateAdministrativeCodeReq;
import com.xushifei.support.beans.dto.req.query.QueryAdministrativeCodeReq;
import com.xushifei.common.dto.ApiResponse;
import com.xushifei.common.util.ResponseUtils;
import com.xushifei.support.beans.dto.resp.AdministrativeCodeResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行政区划编码 前端控制器
 *
 * @author xushifei
 * @since 2022-01-06
 */
@RestController
@RequestMapping("/api/administrative-code")
@RequiredArgsConstructor
public class AdministrativeCodeController {
  private final AdministrativeCodeService administrativeCodeService;

  /**
   * 分页查询
   *
   * @param req 分页查询请求
   * @return
   */
  @PostMapping("/list")
  public ApiResponse<Page<AdministrativeCodeResp>> page(
      @Valid @RequestBody QueryAdministrativeCodeReq req) {
    return ResponseUtils.success(administrativeCodeService.page(req));
  }
  /**
   * 新增行政区划编码
   *
   * @param req 新增行政区划编码请求
   * @return
   */
  @PostMapping("/add")
  public ApiResponse<Object> add(@Valid @RequestBody AddAdministrativeCodeReq req) {
    administrativeCodeService.save(req);
    return ResponseUtils.success();
  }

  /**
   * 删除行政区划编码
   *
   * @param id 行政区划编码id
   * @return
   */
  @PostMapping("/delete")
  public ApiResponse<Object> delete(@RequestParam("id") Long id) {
    administrativeCodeService.removeById(id);
    return ResponseUtils.success();
  }

  /**
   * 更新行政区划编码
   *
   * @param req 更新行政区划编码请求
   * @return
   */
  @PostMapping("/update")
  public ApiResponse<Object> update(@Valid @RequestBody UpdateAdministrativeCodeReq req) {
    administrativeCodeService.update(req);
    return ResponseUtils.success();
  }

  /**
   * 获取行政区划编码
   *
   * @param id 行政区划编码id
   * @return
   */
  @PostMapping("/getDetail")
  public ApiResponse<AdministrativeCodeResp> getDetail(@RequestParam("id") Long id) {
    return ResponseUtils.success(administrativeCodeService.getVOById(id));
  }
}
