package com.xushifei.authorization.server.vo;

import lombok.Data;
import com.xushifei.common.vo.BaseVO;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 客户端分组关联
 * </p>
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientGroupRelationVO extends BaseVO{

    /**
     * 客户端id
     */
    private Long clientId;

    /**
     * 分组id
     */
    private Long groupId;

}
