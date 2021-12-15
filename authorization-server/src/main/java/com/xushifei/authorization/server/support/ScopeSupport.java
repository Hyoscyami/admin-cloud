package com.xushifei.authorization.server.support;

import com.xushifei.authorization.server.entity.Scope;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xushifei.authorization.server.entity.ScopeGroup;

import java.util.List;

/**
 * 权限 服务类
 *
 * @author xushifei
 * @since 2021-12-15
 */
public interface ScopeSupport extends IService<Scope> {
  /**
   * 根据权限组ID查询权限列表
   *
   * @param groupIds
   * @return
   */
  List<Scope> listByGroupIds(List<Long> groupIds);
}
