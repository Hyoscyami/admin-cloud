package com.xushifei.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collections;
import java.util.List;

/**
 * @author xushifei
 * @date 2021/5/17
 */
public class PageUtils {
  /**
   * 空page
   *
   * @param <T>
   * @return
   */
  public static <T> Page<T> emptyDataPage() {
    Page<T> page = new Page<>();
    page.setTotal(0);
    page.setRecords(Collections.emptyList());
    return page;
  }

  /**
   * 组装page
   *
   * @param total
   * @param records
   * @param <T>
   * @return
   */
  public static <T> Page<T> page(Integer total, List<T> records) {
    Page<T> page = new Page<>();
    page.setTotal(total);
    page.setRecords(records);
    return page;
  }
  /**
   * 组装page
   *
   * @param total
   * @param records
   * @param <T>
   * @return
   */
  public static <T> Page<T> page(Long total, List<T> records) {
    Page<T> page = new Page<>();
    page.setTotal(total);
    page.setRecords(records);
    return page;
  }

  /**
   * 手动分页
   *
   * @param page 页码
   * @param size 页大小
   * @param list 待分页list
   * @return
   */
  public static <T> List<T> page(int page, int size, List<T> list) {
    // 计算开始索引
    int fromIndex = (page - 1) * size;
    if (fromIndex >= list.size()) {
      return Collections.emptyList();
    }
    // 计算结束索引
    int toIndex = (list.size() - fromIndex) > size ? fromIndex + size : list.size();
    // 赋值
    return list.subList(fromIndex, toIndex);
  }
}
