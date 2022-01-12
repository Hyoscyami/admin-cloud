package com.xushifei.id.generate.server.job;

import com.xushifei.common.enums.StatusEnum;
import com.xushifei.common.utils.HttpUtils;
import com.xushifei.common.utils.JsonUtils;
import com.xushifei.id.generate.server.dto.EurekaApplicationResp;
import com.xushifei.id.generate.server.dto.EurekaInstanceResp;
import com.xushifei.id.generate.server.entity.SnowflakeAlloc;
import com.xushifei.id.generate.server.enums.IDEnums;
import com.xushifei.id.generate.server.manager.SnowflakeAllocManager;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 雪花算法定时器
 *
 * @author xushifei
 * @date 2022/1/12
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SnowflakeIdJob {
  private final SnowflakeAllocManager snowflakeAllocManager;
  /** eureka注册中心地址 */
  @Value("${eureka.client.serviceUrl.defaultZone}")
  private String eurekaUrl;
  /**
   * 如果发现已经启用的雪花算法记录，注册中心的服务信息不在已经启用的雪花算法记录中，说明该记录已废弃，则标记为弃用状态，进行回收，方便下次服务进行使用
   *
   * @throws Exception
   */
  @XxlJob("clearSnowflakeDeprecatedHandler")
  public void clearSnowflakeDeprecatedHandler() throws Exception {
    log.info("开始检测不再使用的雪花算法记录");
    String serverJson = HttpUtils.get(this.eurekaUrl, Collections.emptyMap(), String.class);
    List<EurekaApplicationResp> list =
        JsonUtils.jsonToArray(serverJson, IDEnums.PATH_KEY.getCode());
    if (ObjectUtils.isEmpty(list)) {
      return;
    }
    list.forEach(this::clearSnowflakeDeprecated);
  }

  /**
   * 清理不再使用的雪花算法记录
   *
   * @param applicationResp
   */
  private void clearSnowflakeDeprecated(EurekaApplicationResp applicationResp) {
    // 当前系统启用的雪花算法记录
    List<SnowflakeAlloc> snowflakeAllocs =
        snowflakeAllocManager
            .lambdaQuery()
            .eq(SnowflakeAlloc::getStatus, StatusEnum.STATUS_ENABLE.getValue())
            .list();
    // eureka实例列表 key is hostname:port
    Map<String, EurekaInstanceResp> eurekaInstanceRespMap =
        applicationResp.getInstanceList().stream()
            .filter(
                eurekaInstanceResp -> {
                  // 服务正常
                  return IDEnums.EUREKA_STATUS_UP.getCode().equals(eurekaInstanceResp.getStatus());
                })
            .collect(
                Collectors.toMap(
                    eurekaInstanceResp ->
                        String.format(
                            IDEnums.SNOWFLAKE_ID_KEY.getCode(),
                            eurekaInstanceResp.getHostname(),
                            eurekaInstanceResp.getPort()),
                    Function.identity()));

    snowflakeAllocs.forEach(
        snowflakeAlloc -> {
          String key =
              String.format(
                  IDEnums.SNOWFLAKE_ID_KEY.getCode(),
                  snowflakeAlloc.getHostname(),
                  snowflakeAlloc.getPort());
          EurekaInstanceResp eurekaInstanceResp = eurekaInstanceRespMap.get(key);
          if (Objects.isNull(eurekaInstanceResp)) {
            // 数据库中已启用，但注册中心未找到，则禁用该数据
            snowflakeAlloc.setStatus(StatusEnum.STATUS_DISABLE.getValue());
            snowflakeAllocManager.updateById(snowflakeAlloc);
          }
        });
  }
}
