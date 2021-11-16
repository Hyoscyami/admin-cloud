package com.xushifei.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * VO父类
 *
 * @author xushifei
 * @since 2021/11/16
 */
@Data
public class BaseVO {
  /** id */
  protected Long id;
  /** 名称 */
  protected String name;
  /** 状态，1：启用，2：禁用 */
  protected Integer status;
  /** 排序值，默认为1 */
  protected Integer sort;
  /** 创建时间 */
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  protected LocalDateTime createTime;

  /** 更新时间 */
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  protected LocalDateTime modifyTime;
  /** 创建人名称 */
  protected String creatorName;
  /** 修改人名称 */
  protected String modifierName;
}
