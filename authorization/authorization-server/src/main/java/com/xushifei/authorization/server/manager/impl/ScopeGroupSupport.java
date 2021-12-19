package com.xushifei.authorization.server.manager.impl;

import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.mapper.ScopeGroupMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限分组 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeGroupSupport extends ServiceImpl<ScopeGroupMapper, ScopeGroup>
    implements com.xushifei.authorization.server.manager.ScopeGroupSupport {
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
