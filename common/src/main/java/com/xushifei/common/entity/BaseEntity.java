package com.xushifei.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xushifei.common.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公共实体类
 *
 * @author xushifei
 * @date 2021/11/16
 */
@Data
public class BaseEntity {
  /** id */
  protected Long id;
  /** 是否被删除，1：被删除，0：未删除 */
  protected Boolean deleted;
  /** 名称 */
  protected String name;
  /** 类型 */
  protected Integer type;
  /** 状态，1：启用，2：禁用 */
  protected Integer status;
  /** 排序值，默认为1 */
  protected Integer sort;
  /** 创建人名称 */
  protected String creatorName;
  /** 修改人名称 */
  protected String modifierName;
  /** 创建时间 */
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  protected LocalDateTime createTime;

  /** 更新时间 */
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  protected LocalDateTime modifyTime;

  /** 创建人id */
  protected Long creatorId;

  /** 更新人id */
  protected Long modifierId;
  /** 编码 */
  protected String code;
  /** 租户id */
  protected Long tenantId;
  /** 备注 */
  protected String note;

  /** 新增时初始化信息 */
  protected void assignCreateInfo() {
    this.deleted = false;
    this.status = StatusEnum.STATUS_ENABLE.getValue();
    this.createTime = LocalDateTime.now();
  }

  /** 更新时初始化信息 */
  protected void assignModifyInfo() {
    this.modifyTime = LocalDateTime.now();
  }

  /** 删除时初始化信息 */
  protected void assignDelInfo() {
    this.modifyTime = LocalDateTime.now();
    this.deleted = true;
  }
}
