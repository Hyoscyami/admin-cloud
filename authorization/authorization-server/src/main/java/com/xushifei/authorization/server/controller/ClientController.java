package com.xushifei.authorization.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xushifei.authorization.server.dto.add.AddClientReq;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.vo.ClientVO;
import com.xushifei.common.dto.ApiResponse;
import com.xushifei.common.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 客户端基本信息 前端控制器
 *
 * @author xushifei
 * @since 2021-12-15
 */
@RestController
@RequestMapping("/api/client")
@Api(tags = "客户端基本信息")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientService;

  /**
   * 新增客户端基本信息
   *
   * @param req
   * @return
   */
  @ApiOperation("新增客户端基本信息")
  @PostMapping("/add")
  public ApiResponse<ClientVO> add(@Valid @RequestBody AddClientReq req) {
    clientService.save(req);
    return ResponseUtils.success();
  }
}
