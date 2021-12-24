package com.xushifei.authorization.server.vo;

import lombok.Data;
import com.xushifei.common.vo.BaseVO;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 权限
 * </p>
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScopeVO extends BaseVO{

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 值
     */
    private String value;

}
