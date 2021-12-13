package com.xushifei.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author xusf
 * @date 2020/9/19 下午5:55
 * @description Json工具类，使用jackson
 */
@Slf4j
public class JsonUtils {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer();
  private static final ObjectReader OBJECT_READER = OBJECT_MAPPER.reader();
  /**
   * 对象转Json
   *
   * @param object 待转换对象
   * @return 对象Json格式
   */
  public static String objectToJson(Object object) {
    if (Objects.isNull(object)) {
      throw new IllegalArgumentException("对象转json字符串时不能为空，请检查方法入参");
    }
    String result = null;
    try {
      result = OBJECT_WRITER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("对象转Json异常:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "对象转Json异常");
    }
    return result;
  }

  /**
   * json转对象
   *
   * @param json
   * @param tClass
   * @param <T>
   * @return
   */
  public static <T> T jsonToObject(String json, Class<T> tClass) {
    if (!StringUtils.hasLength(json)) {
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "json转对象异常，不能解析空的json字符串");
    }
    T t = null;
    try {
      t = OBJECT_READER.readValue(json, tClass);
    } catch (IOException e) {
      log.error("Json转对象异常:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "json转对象异常");
    }
    return t;
  }
}
