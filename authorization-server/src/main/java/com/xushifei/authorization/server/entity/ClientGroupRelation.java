package com.xushifei.authorization.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客户端分组关联
 * </p>
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Getter
@Setter
@TableName("client_group_relation")
public class ClientGroupRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    private Long clientId;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 名称
     */
    private String name;


}
