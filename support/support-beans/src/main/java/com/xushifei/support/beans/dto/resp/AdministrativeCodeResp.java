package com.xushifei.support.beans.dto.resp;

import lombok.Data;
import com.xushifei.common.vo.BaseVO;
import lombok.EqualsAndHashCode;
/**
 * 行政区划编码
 *
 * @author xushifei
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdministrativeCodeResp extends BaseVO {

  /** 父id */
  private Long parentId;
}
