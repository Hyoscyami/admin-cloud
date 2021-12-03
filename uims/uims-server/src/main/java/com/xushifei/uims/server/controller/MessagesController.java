package com.xushifei.uims.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xushifei
 * @date 2021/12/3
 */
@RestController
public class MessagesController {
  @GetMapping("/messages")
  public String[] getMessages() {
    return new String[] {"Message 1", "Message 2", "Message 3"};
  }
}
