package com.xushifei.authorization.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客户端权限关联
 * </p>
 *
 * @author xushifei
 * @since 2022-01-05
 */
@Getter
@Setter
@TableName("client_scope_relation")
public class ClientScopeRelation extends BaseEntity {

    /**
     * 客户端id，即client.id
     */
    private Long clientId;

    /**
     * 权限id，即scope.id
     */
    private Long scopeId;


}
