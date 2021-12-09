package com.xushifei.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xushifei
 * @date 2021/11/9
 */
@RestController
public class HelloController {
  @GetMapping("/")
  public String hello() {
    return "hello";
  }
}
