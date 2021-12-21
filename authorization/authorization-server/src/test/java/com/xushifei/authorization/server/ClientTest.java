package com.xushifei.authorization.server;

import com.xushifei.authorization.server.dto.add.AddClientReq;
import com.xushifei.authorization.server.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author xushifei
 * @date 2021/12/21
 */
@SpringBootTest(classes = AuthorizationApplication.class)
public class ClientTest {
  @Autowired ClientService clientService;

  @Test
  public void test() {
    AddClientReq req = new AddClientReq();
    req.setClientId("2");
    clientService.save(req);
  }
}
