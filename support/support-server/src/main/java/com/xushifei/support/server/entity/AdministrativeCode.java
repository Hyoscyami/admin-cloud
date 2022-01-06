package com.xushifei.support.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xushifei.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 行政区划编码
 * </p>
 *
 * @author xushifei
 * @since 2022-01-06
 */
@Getter
@Setter
@TableName("administrative_code")
public class AdministrativeCode extends BaseEntity {

    /**
     * 父id
     */
    private Long parentId;


}
