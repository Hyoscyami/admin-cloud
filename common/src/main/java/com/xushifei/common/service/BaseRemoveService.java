package com.xushifei.common.service;

import com.xushifei.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * 删除父类 T -> entity
 *
 * @author xushifei
 * @date 2021/12/29
 */
public interface BaseRemoveService<T extends BaseEntity> {
  /**
   * 根据id删除
   *
   * @param id
   */
  default void removeById(Serializable id) {
    // 删除
    T entity = this.checkRemove(id);
    // 删除预处理
    this.preRemove(entity);
    // 根据id逻辑删除
    this.removeById(entity);
  }
  /**
   * 根据id批量删除
   *
   * @param idList
   * @return
   */
  default void removeByIds(Collection<? extends Serializable> idList) {
    idList.forEach(this::removeById);
  }
  /**
   * 校验是否能删除
   *
   * @param id
   * @return
   */
  T checkRemove(Serializable id);

  /**
   * 删除预处理
   *
   * @param entity
   */
  default void preRemove(T entity) {
    entity.assignDelInfo();
  }

  /**
   * 删除持久化
   *
   * @param entity
   */
  void removeById(T entity);
}
