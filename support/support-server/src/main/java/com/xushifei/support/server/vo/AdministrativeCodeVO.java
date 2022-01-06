package com.xushifei.support.server.vo;

import lombok.Data;
import com.xushifei.common.vo.BaseVO;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 行政区划编码
 * </p>
 *
 * @author xushifei
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdministrativeCodeVO extends BaseVO{

    /**
     * 父id
     */
    private Long parentId;

}
