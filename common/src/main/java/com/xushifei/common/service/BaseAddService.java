package com.xushifei.common.service;

import com.xushifei.common.entity.BaseEntity;

/**
 * 新增父类 T -> AddReq S -> entity
 *
 * @author xushifei
 * @date 2021/12/29
 */
public interface BaseAddService<T, S extends BaseEntity> {
  /**
   * 新增请求
   *
   * @param req
   */
  default void add(T req) {
    // 入参校验
    this.checkAdd(req);
    // 入参转实体
    S entity = this.convertAddReqToEntity(req);
    // 入库预处理
    this.preSave(entity);
    // 入库保存
    this.save(entity);
  }

  /**
   * 请求校验
   *
   * @param req
   */
  default void checkAdd(T req) {}

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  S convertAddReqToEntity(T req);

  /**
   * 数据持久化预处理
   *
   * @param entity
   */
  default void preSave(S entity) {}

  /**
   * 数据持久化
   *
   * @param entity
   */
  void save(S entity);
}
