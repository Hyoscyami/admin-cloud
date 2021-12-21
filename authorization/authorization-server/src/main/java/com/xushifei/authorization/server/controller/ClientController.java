package com.xushifei.authorization.server.controller;

import com.xushifei.authorization.server.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
