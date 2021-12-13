package com.xushifei.id.generate.beans.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author xushifei
 * @date 2021/12/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SegmentIdReq extends BaseIdAllocReq {
  /** 业务标识 */
  @NotBlank(message = "bizTag不能为空")
  private String bizTag;
}
