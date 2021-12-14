package com.xushifei.developer.platform.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 免认证接口
 *
 * @author xushifei
 * @date 2021/12/14
 */
@FeignClient(
    value = "developer-platform-server",
    fallback = DeveloperPlatformAuthClientFallback.class)
public interface DeveloperPlatformAuthClient {}
