package com.xushifei.support.beans.dto.req.add;

import lombok.Data;
/**
 * 行政区划编码
 *
 * @author xushifei
 * @since 2022-01-06
 */
@Data
public class AddAdministrativeCodeReq {

  /** 父id */
  private Long parentId;

  /** 名称 */
  private String name;

  /** 类型，1：省级（省份、直辖市、自治区），2：地级（城市），3：县级（区县），4：乡级（乡镇、街道），5：村级（村委会、居委会） */
  private Integer type;

  /** 行政区划编码 */
  private String code;

  /** 是否有效，1：有效，0：无效 */
  private Integer status;
}
