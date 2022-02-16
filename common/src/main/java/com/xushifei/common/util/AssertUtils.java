package com.xushifei.common.util;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author xushifei
 * @date 2020/10/30 14:54
 * @description
 */
public class AssertUtils {
  /**
   * 参数不为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void notNull(@Nullable Object object, String message) {
    if (object == null) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }

  /**
   * 参数为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void isNull(@Nullable Object object, String message) {
    if (object != null) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }

  /**
   * 参数不为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void notBlank(@Nullable String object, String message) {
    if (ObjectUtils.isEmpty(object)) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }

  /**
   * 参数为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void isBlank(@Nullable String object, String message) {
    if (!ObjectUtils.isEmpty(object)) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }

  /**
   * 参数不为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void notEmpty(@Nullable Collection<?> object, String message) {
    if (CollectionUtils.isEmpty(object)) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }

  /**
   * 参数不为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void notEmpty(@Nullable Map<?, ?> object, String message) {
    if (CollectionUtils.isEmpty(object)) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }

  /**
   * 参数为空，否则抛出异常
   *
   * @param object
   * @param message
   */
  public static void isEmpty(@Nullable Collection<?> object, String message) {
    if (!CollectionUtils.isEmpty(object)) {
      throw new BusinessException(ApiCodeEnum.PARAM_ERROR.getCode(), message);
    }
  }
}
