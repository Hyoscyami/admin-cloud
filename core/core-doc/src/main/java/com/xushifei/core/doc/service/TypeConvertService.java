package com.xushifei.core.doc.service;

import com.xushifei.core.doc.dto.DataType;

/**
 * openapi类型转换
 *
 * @author xushifei
 * @date 2022/1/18
 */
public interface TypeConvertService {
  /**
   * 根据类型获取openapi数据对象
   *
   * @param type
   * @return
   */
  DataType get(String type);
}
