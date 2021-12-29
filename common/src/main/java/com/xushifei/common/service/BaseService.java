package com.xushifei.common.service;

import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.vo.BaseVO;

/**
 * 公共父类 T -> AddReq S -> UpdateReq U -> QueryReq V -> VO E -> entity
 *
 * @author xushifei
 * @since 2021/12/18
 */
public interface BaseService<T, S, U extends BaseQueryReq, V extends BaseVO, E extends BaseEntity>
    extends BaseAddService<T, E>,
        BaseRemoveService<E>,
        BaseUpdateService<S, E>,
        BaseQueryService<U, E, V> {}
