package com.xushifei.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
   * json转换
   *
   * @param json
   * @param tClass
   * @param <T>
   * @return
   */
  public static <T> T jsonToObject(String json, Class<T> tClass) {
    AssertUtils.notBlank(json, "json转换异常，不能解析空的json字符串");
    AssertUtils.notNull(tClass, "json转换异常，类不能为空");
    T t = null;
    try {
      t = OBJECT_READER.readValue(json, tClass);
    } catch (IOException e) {
      log.error("Json转换异常:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "json转换异常");
    }
    return t;
  }

  /**
   * json转数组
   *
   * @param json
   * @param <T>
   * @return
   */
  public static <T> List<T> jsonToArray(String json) {
    AssertUtils.notBlank(json, "json转换异常，不能解析空的json字符串");
    List<T> t = null;
    try {
      t = OBJECT_MAPPER.readValue(json, new TypeReference<>() {});
    } catch (IOException e) {
      log.error("Json转换异常:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "json转换异常");
    }
    return t;
  }
  /**
   * 根据key的完整路径查询json节点，并返回json格式，key格式为英文斜杠分割，如/a/b
   *
   * @param json
   * @param pathKey
   * @return
   */
  public static String getJsonByPathKey(String json, String pathKey) {
    AssertUtils.notBlank(json, "json转换异常，不能解析空的json字符串");
    AssertUtils.notBlank(pathKey, "json转换异常，根据key解析时，key不能为空");
    try {
      JsonNode rootNode = OBJECT_READER.readTree(json);
      JsonNode node = rootNode.at(pathKey);
      return node.asText();
    } catch (IOException e) {
      log.error("Json转换异常:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "json转换异常");
    }
  }
  /**
   * 根据key查询json节点，并返回json格式
   *
   * @param json
   * @param key
   * @return
   */
  public static String getJsonByKey(String json, String key) {
    AssertUtils.notBlank(json, "json转换异常，不能解析空的json字符串");
    AssertUtils.notBlank(key, "json转换异常，根据key解析时，key不能为空");
    try {
      JsonNode rootNode = OBJECT_READER.readTree(json);
      JsonNode node = rootNode.get(key);
      return node.asText();
    } catch (IOException e) {
      log.error("Json转换异常:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "json转换异常");
    }
  }
  /**
   * json转数组，先根据completeKey查到节点，然后将节点转数组
   *
   * @param json
   * @param pathKey 节点路径 如/identification/name
   * @param <T>
   * @return
   */
  public static <T> List<T> jsonToArray(String json, String pathKey) {
    AssertUtils.notBlank(json, "json转换异常，不能解析空的json字符串");
    AssertUtils.notBlank(pathKey, "json转换异常，根据key解析时，key不能为空");
    String currentJson = getJsonByPathKey(json, pathKey);
    if (ObjectUtils.isEmpty(currentJson)) {
      return Collections.emptyList();
    }
    return jsonToArray(currentJson);
  }
}
