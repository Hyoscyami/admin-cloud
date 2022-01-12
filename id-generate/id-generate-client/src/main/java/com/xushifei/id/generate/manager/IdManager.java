package com.xushifei.id.generate.manager;

import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;

/**
 * id常用操作封装
 *
 * @author xushifei
 * @date 2022/1/12
 */
public interface IdManager {
  /**
   * 获取号段模式id
   *
   * @param req
   * @return
   */
  Long getSegmentId(SegmentIdReq req);

  /**
   * 获取雪花算法id
   *
   * @return
   */
  Long getSnowflakeId();
}
