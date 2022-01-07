package com.xushifei.core.web.utils;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 当前HttpServletRequest相关
 *
 * @author xushifei
 * @date 2021/4/7
 */
@Slf4j
public class RequestUtils {
  /**
   * 获取当前HttpServletRequest
   *
   * @return
   */
  public static HttpServletRequest getCurrentHttpRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
    throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "获取系统当前HttpServletRequest失败");
  }

  /**
   * 获取当前获取当前HttpServletRequest中header对应的key
   *
   * @param key
   * @return
   */
  public static String getHeader(String key) {
    return getCurrentHttpRequest().getHeader(key);
  }
}
