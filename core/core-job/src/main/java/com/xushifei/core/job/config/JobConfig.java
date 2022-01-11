package com.xushifei.core.job.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时器配置
 *
 * @author xushifei
 * @date 2022/1/11
 */
@Slf4j
@Configuration
@ConditionalOnProperty(
    name = {"job.enable"},
    havingValue = "true")
@RequiredArgsConstructor
public class JobConfig {
  private final JobProperties properties;

  @Bean
  public XxlJobSpringExecutor xxlJobExecutor() {
    log.info(">>>>>>>>>>> xxl-job config init.");
    XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
    xxlJobSpringExecutor.setAdminAddresses(properties.getAdminAddresses());
    xxlJobSpringExecutor.setAppname(properties.getAppName());
    xxlJobSpringExecutor.setAddress(properties.getAddress());
    xxlJobSpringExecutor.setIp(properties.getIp());
    xxlJobSpringExecutor.setPort(properties.getPort());
    xxlJobSpringExecutor.setAccessToken(properties.getAccessToken());
    xxlJobSpringExecutor.setLogPath(properties.getLogPath());
    xxlJobSpringExecutor.setLogRetentionDays(properties.getLogRetentionDays());

    return xxlJobSpringExecutor;
  }
}
