package com.xushifei.authorization.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.xushifei")
@MapperScan({
  "com.baomidou.mybatisplus.samples.quickstart.mapper",
  "com.xushifei.authorization.server.mapper"
})
public class AuthorizationApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthorizationApplication.class, args);
  }
}
