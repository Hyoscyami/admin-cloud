package com.xushifei.authorization.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户端分组关联
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Getter
@Setter
@TableName("client_group_relation")
public class ClientGroupRelation extends BaseEntity {

  /** 客户端id */
  private Long clientId;

  /** 分组id */
  private Long groupId;
}
