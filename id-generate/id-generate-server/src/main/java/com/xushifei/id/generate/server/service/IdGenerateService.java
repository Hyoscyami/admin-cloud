package com.xushifei.id.generate.server.service;

import com.xushifei.id.generate.beans.dto.req.BaseIdAllocReq;

import java.util.List;

/**
 * id生成
 *
 * @author xushifei
 * @date 2021/12/13
 */
public interface IdGenerateService<T> {
  /**
   * 获取单个唯一id
   *
   * @param req
   * @return
   */
  Long getId(T req);

  /**
   * 批量获取唯一id
   *
   * @param req
   * @return
   */
  List<Long> listId(T req);
}
