package com.xushifei.core.doc.dto;

import lombok.Data;

/**
 * This is the root document object of the OpenAPI document.
 *
 * @author xushifei
 * @date 2022/1/18
 */
@Data
public class OpenApi {
  /**
   * REQUIRED. This string MUST be the semantic version number of the OpenAPI Specification version
   * that the OpenAPI document uses. The openapi field SHOULD be used by tooling specifications and
   * clients to interpret the OpenAPI document. This is not related to the API info.version string.
   */
  private String openapi;
}
