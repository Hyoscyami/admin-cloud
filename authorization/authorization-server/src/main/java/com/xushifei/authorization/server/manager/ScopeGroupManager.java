package com.xushifei.authorization.server.manager;

import com.xushifei.authorization.server.entity.ScopeGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 权限分组 服务类
 *
 * @author xushifei
 * @since 2021-12-15
 */
public interface ScopeGroupManager extends IService<ScopeGroup> {
  /**
   * 根据clientId查询分组
   *
   * @param clientId
   * @return
   */
  List<ScopeGroup> listByClientId(Long clientId);
}
