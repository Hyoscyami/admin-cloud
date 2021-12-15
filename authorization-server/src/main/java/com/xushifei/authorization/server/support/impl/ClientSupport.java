package com.xushifei.authorization.server.support.impl;

import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.mapper.ClientMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户端基本信息 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ClientSupport extends ServiceImpl<ClientMapper, Client>
    implements com.xushifei.authorization.server.support.ClientSupport {}