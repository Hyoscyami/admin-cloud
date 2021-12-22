package com.xushifei.authorization.server.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.manager.ScopeGroupManager;
import com.xushifei.authorization.server.mapper.ScopeGroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限分组 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeGroupManagerImpl extends ServiceImpl<ScopeGroupMapper, ScopeGroup>
    implements ScopeGroupManager {
  /**
   * 根据clientId查询分组
   *
   * @param clientId
   * @return
   */
  @Override
  public List<ScopeGroup> listByClientId(Long clientId) {
    return this.baseMapper.listByClientId(clientId);
  }
}
