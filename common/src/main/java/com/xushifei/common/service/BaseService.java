package com.xushifei.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.vo.BaseVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 公共父类
 *
 * @author xushifei
 * @since 2021/12/18
 */
public interface BaseService<T> {
  /**
   * 保存
   *
   * @param req
   * @return
   */
  void save(BaseAddReq req);

  /**
   * 删除
   *
   * @param id
   * @return
   */
  void removeById(Serializable id);

  /**
   * 删除
   *
   * @param idList
   * @return
   */
  void removeByIds(Collection<? extends Serializable> idList);

  /**
   * 更新
   *
   * @param req
   * @return
   */
  void updateById(BaseUpdateReq req);

  /**
   * 详情
   *
   * @param id
   * @return
   */
  BaseVO getVOById(Serializable id);

  /**
   * 分页
   *
   * @param req
   * @return
   */
  Page<BaseVO> page(BaseQueryReq req);
}
