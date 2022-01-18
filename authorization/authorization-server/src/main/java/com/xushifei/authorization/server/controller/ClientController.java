package com.xushifei.authorization.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xushifei.authorization.server.dto.req.add.AddClientReq;
import com.xushifei.authorization.server.dto.req.query.QueryClientReq;
import com.xushifei.authorization.server.dto.req.update.UpdateClientReq;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.vo.ClientVO;
import com.xushifei.common.dto.ApiResponse;
import com.xushifei.common.utils.ResponseUtils;
import com.xushifei.common.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客户端基本信息 前端控制器
 *
 * @author xushifei
 * @since 2021-12-15
 */
@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientService;

  /**
   * 分页查询
   *
   * @param req 分页查询请求
   * @return
   */
  @PostMapping("/list")
  public ApiResponse<Page<ClientVO>> page(@Valid @RequestBody QueryClientReq req) {
    return ResponseUtils.success(clientService.page(req));
  }
  /**
   * 新增客户端基本信息
   *
   * @param req 新增客户端基本信息请求
   * @return
   */
  @PostMapping("/save")
  public ApiResponse<Object> save(@Valid @RequestBody AddClientReq req) {
    clientService.save(req);
    return ResponseUtils.success();
  }

  /**
   * 删除客户端基本信息
   *
   * @param id 客户端id
   * @return
   */
  @PostMapping("/delete")
  public ApiResponse<Object> delete(@RequestParam("id") Long id) {
    clientService.removeById(id);
    return ResponseUtils.success();
  }

  /**
   * 更新客户端基本信息
   *
   * @param req 更新客户端基本信息请求
   * @return
   */
  @PostMapping("/update")
  public ApiResponse<Object> update(@Valid @RequestBody UpdateClientReq req) {
    clientService.update(req);
    return ResponseUtils.success();
  }

  /**
   * 获取客户端基本信息
   *
   * @param id 客户端id
   * @return
   */
  @PostMapping("/getDetail")
  public ApiResponse<ClientVO> getDetail(@RequestParam("id") Long id) {
    return ResponseUtils.success(clientService.getVOById(id));
  }
}
