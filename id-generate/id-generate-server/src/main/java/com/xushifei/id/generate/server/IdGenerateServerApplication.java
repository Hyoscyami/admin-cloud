package com.xushifei.id.generate.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({
  "com.baomidou.mybatisplus.samples.quickstart.mapper",
  "com.xushifei.id.generate.server.mapper"
})
public class IdGenerateServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(IdGenerateServerApplication.class, args);
  }
}
