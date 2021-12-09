package com.xushifei.id.generate.client;

/**
 * <p>
 *熔断
 * </p>
 *
 * @author xushifei
 * @since 2021/12/10
 */
public class IdClientFallBack implements IdClient{
    /**
     * 号段模式
     *
     * @param bizTag 业务标识
     * @return
     */
    @Override
    public Long getSegmentId(String bizTag) {
        return null;
    }

    /**
     * 雪花算法
     *
     * @return
     */
    @Override
    public Long getSnowflakeId() {
        return null;
    }
}
