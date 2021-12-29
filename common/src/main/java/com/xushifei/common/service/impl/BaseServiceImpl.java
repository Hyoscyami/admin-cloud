package com.xushifei.common.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * 公共父类 之前增删改查写完，发现父类代码行数有点长，试下增删改查接口抽出来 M -> manager T -> AddReq S -> UpdateReq U -> QueryReq V ->
 * VO E -> entity 这么多泛型感觉挺丑的，暂时没想到什么办法，如果直接使用父类然后强转子类
 *
 * @author xushifei
 * @since 2021/12/18
 */
public abstract class BaseServiceImpl<
        M extends IService<E>, T, S, U extends BaseQueryReq, V extends BaseVO, E extends BaseEntity>
    implements com.xushifei.common.service.BaseAddService<T, E>,
        com.xushifei.common.service.BaseRemoveService<E>,
        com.xushifei.common.service.BaseUpdateService<S, E>,
        com.xushifei.common.service.BaseQueryService<U, E, V> {
  @Autowired protected M manager;

  /**
   * 根据id获取实体
   *
   * @param id
   * @return
   */
  @Override
  public E getById(Serializable id) {
    return manager.getById(id);
  }

  /**
   * 保存实体
   *
   * @param entity
   */
  @Override
  public void save(E entity) {
    manager.save(entity);
  }

  /**
   * 批量删除
   *
   * @param idList
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void removeByIds(Collection<? extends Serializable> idList) {
    idList.forEach(this::removeById);
  }

  /**
   * 根据id更新
   *
   * @param entity
   */
  @Override
  public void updateById(E entity) {
    manager.updateById(entity);
  }

  /**
   * 根据id删除
   *
   * @param entity
   */
  @Override
  public void removeById(E entity) {
    manager.removeById(entity);
  }

  /**
   * 校验是否能删除
   *
   * @param id
   * @return
   */
  @Override
  public E checkRemove(Serializable id) {
    return manager.getById(id);
  }
}
