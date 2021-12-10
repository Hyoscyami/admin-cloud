package com.xushifei.id.generate.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 号段模式id分配表
 *
 * @author xushifei
 * @since 2021-12-10
 */
@Getter
@Setter
@TableName("segment_alloc")
public class SegmentAlloc extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /** 业务标识 */
  private String bizTag;

  /** 最大id */
  private Long maxId;

  /** 步长 */
  private Integer step;

  /** 类型 */
  private Integer type;

  /** 编码 */
  private String code;

  /** 排序，默认为1 */
  private Integer sort;

  /** 是否有效，1：有效，0：无效 */
  private Integer status;

  /** 备注 */
  private String note;

  /** 租户id */
  private Long tenantId;

  /** 创建时间 */
  private LocalDateTime createTime;

  /** 创建人id */
  private Long creatorId;

  /** 创建人名称 */
  private String creatorName;

  /** 更新时间 */
  private LocalDateTime modifyTime;

  /** 更新人id */
  private Long modifierId;

  /** 修改人名称 */
  private String modifierName;
}
