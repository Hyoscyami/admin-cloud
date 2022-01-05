package com.xushifei.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import com.xushifei.common.service.*;
import com.xushifei.common.utils.PageUtils;
import com.xushifei.common.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 公共父类 M -> manager T -> AddReq S -> UpdateReq U -> QueryReq V -> VO E -> entity
 * 这么多泛型感觉挺丑的，暂时没想到什么办法，如果直接使用父类然后强转子类
 *
 * @author xushifei
 * @since 2021/12/18
 */
public abstract class BaseServiceImpl<
        M extends IService<E>, T, S, U extends BaseQueryReq, V extends BaseVO, E extends BaseEntity>
    implements BaseService<T, S, U, V> {
  @Autowired protected M manager;
  /**
   * 新增请求
   *
   * @param req
   */
  @Override
  public void save(T req) {
    // 入参校验
    this.checkAdd(req);
    // 入参转实体
    E entity = this.convertAddReqToEntity(req);
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
    E entity = this.checkRemove(id);
    // 删除预处理
    this.preRemove(entity);
    // 根据id逻辑删除
    this.removeById(entity);
  }

  /**
   * 更新
   *
   * @param req
   * @return
   */
  @Override
  public void update(S req) {
    // 校验是否能更新
    this.checkUpdate(req);
    // 转实体
    E entity = this.convertUpdateReqToEntity(req);
    // 更新预处理
    this.preUpdate(entity);
    // 更新入库
    this.updateById(entity);
  }

  /**
   * 详情
   *
   * @param id
   * @return
   */
  @Override
  public V getVOById(Serializable id) {
    E entity = this.getById(id);
    return this.convertEntityToVO(entity);
  }

  /**
   * 分页
   *
   * @param req
   * @return
   */
  @Override
  public Page<V> page(U req) {
    // 分页预处理
    this.prePage(req);
    Long count = this.countVO(req);
    if (count == 0) {
      return PageUtils.emptyDataPage();
    }
    Page<V> page = new Page<>();
    page.setTotal(count);
    page.setRecords(this.listVO(req));
    return page;
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
   * 根据id更新入库
   *
   * @param entity
   */
  protected void updateById(E entity) {
    this.manager.updateById(entity);
  }
  /**
   * 校验是否能删除
   *
   * @param id
   * @return
   */
  protected E checkRemove(Serializable id) {
    return this.manager.getById(id);
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  protected void preRemove(E entity) {
    entity.assignDelInfo();
  }
  /**
   * 删除持久化
   *
   * @param entity
   */
  protected void removeById(E entity) {
    this.manager.removeById(entity);
  }
  /**
   * 校验更新
   *
   * @param req
   */
  protected void checkUpdate(S req) {}

  /**
   * 请求转实体
   *
   * @param req
   * @return
   */
  protected E convertUpdateReqToEntity(S req) {
    throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), "暂不支持更新操作");
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  protected void preUpdate(E entity) {
    entity.assignModifyInfo();
  }
  /**
   * 根据id查实体
   *
   * @param id
   * @return
   */
  protected E getById(Serializable id) {
    return this.manager.getById(id);
  }

  /**
   * 根据实体转VO
   *
   * @param entity
   * @return
   */
  protected V convertEntityToVO(E entity) {
    throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), "暂不支持查看详情");
  }
  /**
   * 统计总数
   *
   * @param req
   * @return
   */
  protected Long countVO(U req) {
    return 0L;
  }

  /**
   * VO列表
   *
   * @param req
   * @return
   */
  protected List<V> listVO(U req) {
    return Collections.emptyList();
  }
  /**
   * 分页参数预处理
   *
   * @param req
   */
  protected void prePage(U req) {
    req.calculateOffset();
  }
  /**
   * 请求校验
   *
   * @param req
   */
  protected void checkAdd(T req) {}

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  protected abstract E convertAddReqToEntity(T req);

  /**
   * 数据持久化预处理
   *
   * @param entity
   */
  protected void preSave(E entity) {
    entity.assignCreateInfo();
  }

  /**
   * 保存实体
   *
   * @param entity
   */
  protected void save(E entity) {
    this.manager.save(entity);
  }
}
