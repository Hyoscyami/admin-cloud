package com.xushifei.authorization.server.support.impl;

import com.xushifei.authorization.server.entity.Scope;
import com.xushifei.authorization.server.entity.ScopeGroup;
import com.xushifei.authorization.server.mapper.ScopeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ScopeSupport extends ServiceImpl<ScopeMapper, Scope>
    implements com.xushifei.authorization.server.support.ScopeSupport {
  /**
   * 根据权限组ID查询权限列表
   *
   * @param groupIds
   * @return
   */
  @Override
  public List<Scope> listByGroupIds(List<Long> groupIds) {
    return this.baseMapper.listByGroupIds(groupIds);
  }
}
