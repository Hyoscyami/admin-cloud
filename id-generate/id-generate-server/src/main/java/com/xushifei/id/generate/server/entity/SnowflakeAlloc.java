package com.xushifei.id.generate.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 雪花算法分配id
 * </p>
 *
 * @author xushifei
 * @since 2022-01-12
 */
@Getter
@Setter
@TableName("snowflake_alloc")
public class SnowflakeAlloc extends BaseEntity {

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 雪花算法的机器id
     */
    private Integer workerId;

    /**
     * 服务名
     */
    private String serverName;


}
