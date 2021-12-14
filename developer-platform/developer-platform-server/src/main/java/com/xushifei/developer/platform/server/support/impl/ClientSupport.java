package com.xushifei.developer.platform.server.support.impl;

import com.xushifei.developer.platform.server.entity.Client;
import com.xushifei.developer.platform.server.mapper.ClientMapper;
import com.xushifei.developer.platform.server.support.IClientSupport;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端基本信息 服务实现类
 * </p>
 *
 * @author xushifei
 * @since 2021-12-14
 */
@Service
public class ClientSupport extends ServiceImpl<ClientMapper, Client> implements IClientSupport {

}
