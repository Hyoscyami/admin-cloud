package com.xushifei.authorization.server.mapper;

import com.xushifei.authorization.server.entity.ScopeGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限分组 Mapper 接口
 *
 * @author xushifei
 * @since 2021-12-15
 */
public interface ScopeGroupMapper extends BaseMapper<ScopeGroup> {
  /**
   * 根据clientId查询分组
   *
   * @param clientId
   * @return
   */
  List<ScopeGroup> listByClientId(@Param("clientId") Long clientId);
}
