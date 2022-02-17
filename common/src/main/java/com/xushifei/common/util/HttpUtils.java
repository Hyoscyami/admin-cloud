package com.xushifei.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * http工具类
 *
 * @author xushifei
 * @date 2021/6/16
 */
@Slf4j
public class HttpUtils {
  private static final RestTemplate REST_TEMPLATE = new RestTemplate();

  /**
   * post json请求，返回json报文
   *
   * @param requestUrl 请求地址
   * @param requestObj 请求对象
   * @param clazz 返回类
   * @param <T>
   * @return
   */
  public static <T> T postJson(final String requestUrl, Object requestObj, Class<T> clazz) {
    AssertUtils.notBlank(requestUrl, "请求接口地址不能为空");
    AssertUtils.notNull(requestObj, "请求参数不能为空");
    AssertUtils.notNull(clazz, "返回类型不能为空");

    String requestJson = JsonUtils.objectToJson(requestObj);
    log.info("post json请求地址:{},请求报文:{}", requestUrl, requestJson);
    ResponseEntity<T> postForEntity =
        REST_TEMPLATE.postForEntity(requestUrl, getJsonHttpEntity(requestJson), clazz);
    log.info("post json 响应报文:{}", postForEntity);
    return postForEntity.getBody();
  }

  /**
   * post json请求，返回json报文，支持嵌套泛型
   *
   * @param requestUrl 请求地址
   * @param requestObj 请求对象
   * @param typeReference 泛型嵌套
   * @param <T>
   * @return
   */
  public static <T> T postJson(
      final String requestUrl, Object requestObj, ParameterizedTypeReference<T> typeReference) {
    AssertUtils.notBlank(requestUrl, "请求接口地址不能为空");
    AssertUtils.notNull(requestObj, "请求参数不能为空");
    AssertUtils.notNull(typeReference, "泛型类型不能为空");

    String requestJson = JsonUtils.objectToJson(requestObj);
    log.info("post json请求地址:{},请求报文:{}", requestUrl, requestJson);
    ResponseEntity<T> postForEntity =
        REST_TEMPLATE.exchange(
            requestUrl, HttpMethod.POST, getJsonHttpEntity(requestJson), typeReference);
    log.info("post json 响应报文:{}", postForEntity);
    return postForEntity.getBody();
  }

  /**
   * get请求，返回json报文
   *
   * @param requestUrl 请求地址
   * @param paramsMap 参数
   * @param clazz 返回类
   * @param <T>
   * @return
   */
  public static <T> T get(final String requestUrl, Map<String, String> paramsMap, Class<T> clazz) {
    AssertUtils.notBlank(requestUrl, "请求接口地址不能为空");
    AssertUtils.notEmpty(paramsMap, "请求参数不能为空");
    AssertUtils.notNull(clazz, "返回类型不能为空");

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<T> httpEntity = new HttpEntity<>(httpHeaders);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestUrl);
    paramsMap.forEach(builder::queryParam);
    // 带上了参数的url
    URI requestBuildUrl = builder.build().encode().toUri();
    log.info("get json请求地址:{},请求报文:{}", requestUrl, requestBuildUrl);
    ResponseEntity<T> responseEntity =
        REST_TEMPLATE.exchange(requestBuildUrl, HttpMethod.GET, httpEntity, clazz);
    log.info("get json响应报文:{}", responseEntity);
    return responseEntity.getBody();
  }

  /**
   * 获取json的http请求实体
   *
   * @param requestObj
   */
  private static HttpEntity<String> getJsonHttpEntity(Object requestObj) {
    return getJsonHttpEntity(JsonUtils.objectToJson(requestObj));
  }

  /**
   * 获取json的http请求实体
   *
   * @param requestJson
   * @return
   */
  private static HttpEntity<String> getJsonHttpEntity(final String requestJson) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(requestJson, httpHeaders);
  }
}
