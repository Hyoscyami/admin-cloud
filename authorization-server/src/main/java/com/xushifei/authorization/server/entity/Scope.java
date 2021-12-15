package com.xushifei.authorization.server.entity;

import com.xushifei.common.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Getter
@Setter
public class Scope extends BaseEntity {

  /** 父id */
  private Long parentId;

  /** 值 */
  private String value;
}
