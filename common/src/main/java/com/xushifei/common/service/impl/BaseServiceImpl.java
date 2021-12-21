package com.xushifei.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import com.xushifei.common.service.BaseService;
import com.xushifei.common.utils.PageUtils;
import com.xushifei.common.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 公共父类
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
   */
  protected void checkAdd(BaseAddReq req) {}

  /**
   * 请求转实体
   *
   * @param req
   * @return
   */
  protected T convertAddReqToEntity(BaseAddReq req) {
    throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "请子类重写新增时请求转换成实体接口");
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  protected void preSave(T entity) {}

  /**
   * 保存实体
   *
   * @param entity
   */
  protected void save(T entity) {
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
  public void save(BaseAddReq req) {
    // 入参校验
    this.checkAdd(req);
    // 入参转实体
    T entity = this.convertAddReqToEntity(req);
    // 入库预处理
    this.preSave(entity);
    // 入库保存
    this.save(entity);
  }

  /**
   * 删除
   *
   * @param id
   * @return
   */
  @Override
  public void removeById(Serializable id) {
    // 删除
    T entity = this.checkRemove(id);
    // 删除预处理
    this.preRemove(entity);
  }

  /**
   * 检验是否能删除
   *
   * @param id
   */
  protected T checkRemove(Serializable id) {
    return manager.getById(id);
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  protected void preRemove(T entity) {}

  /**
   * 删除
   *
   * @param idList
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void removeByIds(Collection<? extends Serializable> idList) {
    idList.forEach(this::removeById);
  }

  /**
   * 更新
   *
   * @param req
   * @return
   */
  @Override
  public void updateById(BaseUpdateReq req) {
    // 校验是否能更新
    this.checkUpdate(req);
    // 转实体
    T entity = this.convertUpdateReqToEntity(req);
    // 更新预处理
    this.preUpdate(entity);
    // 更新入库
    this.updateById(entity);
  }

  /**
   * 更新
   *
   * @param entity
   */
  protected void updateById(T entity) {
    manager.updateById(entity);
  }
  /**
   * 更新预处理
   *
   * @param entity
   */
  protected void preUpdate(T entity) {}

  /**
   * 更新请求转entity
   *
   * @param req
   * @return
   */
  protected T convertUpdateReqToEntity(BaseUpdateReq req) {
    throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "请子类重写更新时请求转换成实体接口");
  }

  /**
   * 校验是否能更新
   *
   * @param req
   */
  protected void checkUpdate(BaseUpdateReq req) {}

  /**
   * 详情
   *
   * @param id
   * @return
   */
  @Override
  public BaseVO getVOById(Serializable id) {
    T entity = manager.getById(id);
    return null;
  }

  /**
   * entity转VO
   *
   * @param entity
   * @return
   */
  protected BaseVO convertEntityToVO(T entity) {
    throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "请子类重写详情时请求转换成实体接口");
  }

  /**
   * 分页
   *
   * @param req
   * @return
   */
  @Override
  public Page<BaseVO> page(BaseQueryReq req) {
    req.calculateOffset();
    Long count = this.countVO(req);
    if (count == 0) {
      return PageUtils.emptyDataPage();
    }
    Page<BaseVO> page = new Page<>();
    page.setTotal(count);
    page.setRecords(this.listVO(req));
    return page;
  }

  /**
   * vo列表
   *
   * @param req
   * @return
   */
  protected List<BaseVO> listVO(BaseQueryReq req) {
    return Collections.emptyList();
  }

  /**
   * 统计总数
   *
   * @param req
   * @return
   */
  protected Long countVO(BaseQueryReq req) {
    return 0L;
  }
}
