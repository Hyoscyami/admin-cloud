package com.xushifei.authorization.server.dto.req.add;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 新增权限
 *
 * @author xushifei
 * @date 2021/12/16
 */
@Data
public class AddScopeReq {
  /** 是否被删除，1：被删除，0：未删除 */
  private Boolean deleted;
  /** 名称 */
  private String name;
  /** 类型 */
  private Integer type;
  /** 状态，1：启用，2：禁用 */
  private Integer status;
  /** 排序值，默认为1 */
  private Integer sort;
  /** 创建人名称 */
  private String creatorName;
  /** 修改人名称 */
  private String modifierName;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 更新时间 */
  private LocalDateTime modifyTime;
  /** 创建人id */
  private Long creatorId;
  /** 更新人id */
  private Long modifierId;
  /** 编码 */
  private String code;
  /** 租户id */
  private Long tenantId;
  /** 备注 */
  private String note;
}
