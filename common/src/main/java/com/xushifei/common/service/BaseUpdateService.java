package com.xushifei.common.service;

import com.xushifei.common.entity.BaseEntity;

/**
 * 更新父类，T -> UpdateReq,S -> entity
 *
 * @author xushifei
 * @date 2021/12/29
 */
public interface BaseUpdateService<T, S extends BaseEntity> {
  /**
   * 更新
   *
   * @param req
   * @return
   */
  default void update(T req) {
    // 校验是否能更新
    this.checkUpdate(req);
    // 转实体
    S entity = this.convertUpdateReqToEntity(req);
    // 更新预处理
    this.preUpdate(entity);
    // 更新入库
    this.updateById(entity);
  }

  /**
   * 根据id更新入库
   *
   * @param entity
   */
  void updateById(S entity);
  /**
   * 校验更新
   *
   * @param req
   */
  default void checkUpdate(T req) {}

  /**
   * 请求转实体
   *
   * @param req
   * @return
   */
  S convertUpdateReqToEntity(T req);

  /**
   * 更新预处理
   *
   * @param entity
   */
  default void preUpdate(S entity) {
    entity.assignModifyInfo();
  }
}
