package com.xushifei.common.dto;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author xushifei
 * @date 2021/11/16
 */
@Data
public class BaseQueryReq {
  /** 页码 */
  protected Integer page;
  /** 页大小 */
  protected Integer size;
  /** 分页起始偏移量 */
  protected Integer offset;
  /** 编码 */
  protected List<String> codes;
  /** 类型 */
  protected List<Integer> types;
  /** 是否启用，1：启用，0：禁用 */
  protected List<Integer> status;
  /** 名称 */
  protected String name;
  /**
   * 需要分页
   *
   * @return
   */
  public Boolean needPage() {
    return Objects.nonNull(this.getPage()) && Objects.nonNull(this.getSize());
  }

  /**
   * 根据分页参数计算偏移量偏移量
   *
   * @return
   */
  public void calculateOffset() {
    if (!this.needPage()) {
      return;
    }
    this.offset = (page - 1) * size;
  }
}
