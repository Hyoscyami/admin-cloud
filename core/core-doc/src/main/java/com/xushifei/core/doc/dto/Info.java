package com.xushifei.core.doc.dto;

import lombok.Data;

/**
 * @author xushifei
 * @date 2022/1/18
 */
@Data
public class Info {
  /** REQUIRED. The title of the API. */
  private String title;
  /** A short description of the API. CommonMark syntax MAY be used for rich text representation */
  private String description;
  /** A URL to the Terms of Service for the API. MUST be in the format of a URL. */
  private String termsOfService;
  /**
   * REQUIRED. The version of the OpenAPI document (which is distinct from the OpenAPI Specification
   * version or the API implementation version).
   */
  private String version;
}
