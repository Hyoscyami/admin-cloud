package com.xushifei.common.service;

import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 公共父类
 *
 * @author xushifei
 * @since 2021/12/18
 */
public interface BaseService<T extends BaseEntity> {
  /**
   * 保存
   *
   * @param req
   * @return
   */
  void save(BaseAddReq req);
  /**
   * 批量保存
   *
   * @param entityList
   * @param batchSize
   * @return
   */
  boolean saveBatch(Collection<T> entityList, int batchSize);

  /**
   * 批量保存
   *
   * @param entityList
   * @return
   */
  boolean saveBatch(Collection<T> entityList);

  /**
   * 删除
   *
   * @param id
   * @return
   */
  boolean removeById(Serializable id);

  /**
   * 删除
   *
   * @param idList
   * @return
   */
  boolean removeByIds(Collection<? extends Serializable> idList);

  /**
   * 更新
   *
   * @param entity
   * @return
   */
  boolean updateById(T entity);

  /**
   * 批量更新
   *
   * @param entityList
   * @return
   */
  boolean updateBatchById(Collection<T> entityList);

  /**
   * 批量更新
   *
   * @param entityList
   * @param batchSize
   */
  boolean updateBatchById(Collection<T> entityList, int batchSize);

  /**
   * 详情
   *
   * @param id
   * @return
   */
  T getById(Serializable id);

  /**
   * 批量根据ID查询
   *
   * @param idList
   * @return
   */
  List<T> listByIds(Collection<? extends Serializable> idList);
}
