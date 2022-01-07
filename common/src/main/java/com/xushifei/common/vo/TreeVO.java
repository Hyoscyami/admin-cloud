package com.xushifei.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author xushifei
 * @date 2022/1/7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeVO extends BaseVO {
  /** 节点父ID */
  protected Long parentId;
  /** 子节点 */
  protected List<TreeVO> children;
}
