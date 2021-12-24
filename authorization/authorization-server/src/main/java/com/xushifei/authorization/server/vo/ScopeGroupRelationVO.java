package com.xushifei.authorization.server.vo;

import lombok.Data;
import com.xushifei.common.vo.BaseVO;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 权限分组关联
 * </p>
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScopeGroupRelationVO extends BaseVO{

    /**
     * 权限id
     */
    private Long scopeId;

    /**
     * 分组id
     */
    private Long groupId;

}
