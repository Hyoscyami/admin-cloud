package com.xushifei.common.utils;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xushifei
 * @date 2020/9/21 9:53
 * @description 接口响应工具类
 */
@Slf4j
public class ResponseUtils {

  public static <T> ApiResponse<T> success() {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.setCode(ApiCodeEnum.SUCCESS.getCode());
    apiResponse.setMsg(ApiCodeEnum.SUCCESS.getMsg());
    return apiResponse;
  }

  public static <T> ApiResponse<T> success(T object) {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.setCode(ApiCodeEnum.SUCCESS.getCode());
    apiResponse.setMsg(ApiCodeEnum.SUCCESS.getMsg());
    apiResponse.setData(object);
    return apiResponse;
  }

  public static <T> ApiResponse<T> fail(ApiCodeEnum apiCodeEnum) {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.setCode(apiCodeEnum.getCode());
    apiResponse.setMsg(apiCodeEnum.getMsg());
    return apiResponse;
  }

  public static <T> ApiResponse<T> fail(final String code, final String msg) {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.setCode(code);
    apiResponse.setMsg(msg);
    return apiResponse;
  }
}
