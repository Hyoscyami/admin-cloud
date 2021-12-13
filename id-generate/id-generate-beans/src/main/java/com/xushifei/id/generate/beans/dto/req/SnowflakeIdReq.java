package com.xushifei.id.generate.beans.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 雪花算法id生成请求类
 *
 * @author xushifei
 * @date 2021/12/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SnowflakeIdReq extends BaseIdAllocReq {
  /** 主机名 */
  private String hostName;
  /** 服务端口 */
  private String serverPort;
}
