package com.xushifei.admin.controller;

import com.xushifei.id.generate.manager.IdManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xushifei
 * @date 2021/11/9
 */
@RestController
public class HelloController {
  @Autowired private IdManagerImpl idManager;

  @GetMapping("/")
  public String hello() {
    return "hello";
  }
}
