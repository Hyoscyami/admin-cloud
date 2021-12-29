package com.xushifei.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.utils.PageUtils;
import com.xushifei.common.vo.BaseVO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 查询父类 T -> QueryReq S -> entity U -> VO
 *
 * @author xushifei
 * @date 2021/12/29
 */
public interface BaseQueryService<T extends BaseQueryReq, S extends BaseEntity, U extends BaseVO> {
  /**
   * 根据id获取VO
   *
   * @param id
   * @return
   */
  default U getVOById(Serializable id) {
    S entity = this.getById(id);
    return this.convertEntityToVO(entity);
  }

  /**
   * 分页
   *
   * @param req
   * @return
   */
  default Page<U> page(T req) {
    // 分页预处理
    this.prePage(req);
    Long count = this.countVO(req);
    if (count == 0) {
      return PageUtils.emptyDataPage();
    }
    Page<U> page = new Page<>();
    page.setTotal(count);
    page.setRecords(this.listVO(req));
    return page;
  }

  /**
   * 统计总数
   *
   * @param req
   * @return
   */
  default Long countVO(T req) {
    return 0L;
  }

  /**
   * VO列表
   *
   * @param req
   * @return
   */
  default List<U> listVO(T req) {
    return Collections.emptyList();
  }
  /**
   * 分页参数预处理
   *
   * @param req
   */
  default void prePage(T req) {
    req.calculateOffset();
  }
  /**
   * 根据id查实体
   *
   * @param id
   * @return
   */
  S getById(Serializable id);

  /**
   * 根据实体转VO
   *
   * @param entity
   * @return
   */
  U convertEntityToVO(S entity);
}
