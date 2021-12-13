package com.xushifei.id.generate.beans.dto.req;

import lombok.Data;

/**
 * id分配父类
 *
 * @author xushifei
 * @date 2021/12/13
 */
@Data
public class BaseIdAllocReq {
  /** 批量分配id数量 */
  protected Integer batchAllocIdNums;
}
