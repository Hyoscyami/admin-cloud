package com.xushifei.authorization.server.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xushifei.authorization.server.entity.Client;
import com.xushifei.authorization.server.manager.IClientManager;
import com.xushifei.authorization.server.mapper.ClientMapper;
import org.springframework.stereotype.Service;

/**
 * 客户端基本信息 服务实现类
 *
 * @author xushifei
 * @since 2021-12-15
 */
@Service
public class ClientManager extends ServiceImpl<ClientMapper, Client> implements IClientManager {}
