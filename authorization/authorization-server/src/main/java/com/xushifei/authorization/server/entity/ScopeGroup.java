package com.xushifei.authorization.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限分组
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Getter
@Setter
@TableName("scope_group")
public class ScopeGroup extends BaseEntity {}
