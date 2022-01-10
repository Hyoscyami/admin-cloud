package com.xushifei.core.mq.manager;

import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * rocketMQ
 *
 * @author xushifei
 * @date 2022/1/7
 */
@Service
@RequiredArgsConstructor
public class RocketMQManager implements MQManager {
  private final RocketMQTemplate mqTemplate;
  /**
   * 发送消息
   *
   * @param destination
   * @param message
   */
  @Override
  public void send(String destination, String message) {
    mqTemplate.convertAndSend(destination, message);
  }
}
