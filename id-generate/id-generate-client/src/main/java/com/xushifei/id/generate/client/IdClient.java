package com.xushifei.id.generate.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 分布式ID
 * </p>
 *
 * @author xushifei
 * @since 2021/12/10
 */
@FeignClient(value = "id-generate-server", fallback = IdClientFallBack.class)
public interface IdClient {
    /**
     * 号段模式
     * @param bizTag 业务标识
     * @return
     */
    Long getSegmentId(String bizTag);

    /**
     * 雪花算法
     * @return
     */
    Long getSnowflakeId();
}
