package com.xushifei.common.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 条件控制
 *
 * @author xushifei
 * @date 2021/5/14
 */
public class ConditionUtils {
  /**
   * 非空，接收
   *
   * @param value
   * @param consumer
   * @param <T>
   */
  public static <T> void acceptIfNotNull(T value, Consumer<T> consumer) {
    if (Objects.nonNull(value)) {
      consumer.accept(value);
    }
  }
  /**
   * 属性非空，接收
   *
   * @param property
   * @param consumer
   * @param <T>
   */
  public static <T> void acceptPropertyIfNotNull(
      Boolean condition, T property, Consumer<T> consumer) {

    if (Objects.nonNull(property)) {
      consumer.accept(property);
    }
  }
  /**
   * 非空，接收
   *
   * @param value
   * @param consumer
   */
  public static void acceptIfNotBlank(String value, Consumer<String> consumer) {
    if (StringUtils.hasLength(value)) {
      consumer.accept(value);
    }
  }
  /**
   * 非空，接收
   *
   * @param value
   * @param consumer
   * @param <T>
   */
  public static <T> void acceptIfNotEmpty(Collection<T> value, Consumer<Collection<T>> consumer) {
    if (CollectionUtils.isEmpty(value)) {
      consumer.accept(value);
    }
  }
}
