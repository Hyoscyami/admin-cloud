package com.xushifei.uims.auth.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 授权服务器调用，接口免校验
 *
 * @author xushifei
 * @date 2021/12/1
 */
@FeignClient(value = "uims-server", fallback = AuthenticationClientFallBack.class)
public interface AuthenticationClient {}
