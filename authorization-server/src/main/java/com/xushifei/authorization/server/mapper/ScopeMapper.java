package com.xushifei.authorization.server.mapper;

import com.xushifei.authorization.server.entity.Scope;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xushifei.authorization.server.entity.ScopeGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限 Mapper 接口
 *
 * @author xushifei
 * @since 2021-12-15
 */
public interface ScopeMapper extends BaseMapper<Scope> {
  /**
   * 根据权限组ID查询权限列表
   *
   * @param groupIds
   * @return
   */
  List<Scope> listByGroupIds(@Param("groupIds") List<Long> groupIds);
}
