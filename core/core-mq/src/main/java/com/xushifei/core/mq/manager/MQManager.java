package com.xushifei.core.mq.manager;

/**
 * MQ接口
 *
 * @author xushifei
 * @date 2022/1/10
 */
public interface MQManager {
  /**
   * 发送消息
   *
   * @param destination
   * @param message
   */
  void send(String destination, String message);
}
