package com.xushifei.common.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import com.xushifei.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 公共父类 s 是 support类
 *
 * @author xushifei
 * @since 2021/12/18
 */
public abstract class BaseServiceImpl<M extends IService<T>, T extends BaseEntity>
    implements BaseService<T> {
  @Autowired protected M manager;
  /**
   * 新增入参检验
   *
   * @param req
   * @param <A>
   */
  protected <A extends BaseAddReq> void checkAdd(A req) {}

  /**
   * 请求转实体
   *
   * @param <A>
   * @return
   */
  protected <A extends BaseAddReq> T convertAddReqToEntity(A req) {
    throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "请子类重写新增时请求转换成实体接口");
  }

  /**
   * 保存实体
   *
   * @param entity
   */
  protected void defaultSave(T entity) {
    // 入库保存
    manager.save(entity);
  }

  /**
   * 保存
   *
   * @param req
   * @return
   */
  @Override
  public <A extends BaseAddReq> void save(A req) {
    // 入参校验
    this.checkAdd(req);
    // 入参转实体
    T entity = this.convertAddReqToEntity(req);
    // 入库保存
    this.defaultSave(entity);
  }

  /**
   * 批量保存
   *
   * @param entityList
   * @param batchSize
   * @return
   */
  @Override
  public boolean saveBatch(Collection<T> entityList, int batchSize) {
    return false;
  }

  /**
   * 批量保存
   *
   * @param entityList
   * @return
   */
  @Override
  public boolean saveBatch(Collection<T> entityList) {
    return false;
  }

  /**
   * 删除
   *
   * @param id
   * @return
   */
  @Override
  public boolean removeById(Serializable id) {
    return false;
  }

  /**
   * 删除
   *
   * @param idList
   * @return
   */
  @Override
  public boolean removeByIds(Collection<? extends Serializable> idList) {
    return false;
  }

  /**
   * 更新
   *
   * @param entity
   * @return
   */
  @Override
  public boolean updateById(T entity) {
    return false;
  }

  /**
   * 批量更新
   *
   * @param entityList
   * @return
   */
  @Override
  public boolean updateBatchById(Collection<T> entityList) {
    return false;
  }

  /**
   * 批量更新
   *
   * @param entityList
   * @param batchSize
   */
  @Override
  public boolean updateBatchById(Collection<T> entityList, int batchSize) {
    return false;
  }

  /**
   * 详情
   *
   * @param id
   * @return
   */
  @Override
  public T getById(Serializable id) {
    return null;
  }

  /**
   * 批量根据ID查询
   *
   * @param idList
   * @return
   */
  @Override
  public List<T> listByIds(Collection<? extends Serializable> idList) {
    return null;
  }
}
