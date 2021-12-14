package com.xushifei.authorization.server.service;

import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;

/**
 * redis保存授权信息
 *
 * @author xushifei
 * @date 2021/12/3
 */
public class RedisOauth2AuthorizationService implements OAuth2AuthorizationService {
  /**
   * Saves the {@link OAuth2Authorization}.
   *
   * @param authorization the {@link OAuth2Authorization}
   */
  @Override
  public void save(OAuth2Authorization authorization) {}

  /**
   * Removes the {@link OAuth2Authorization}.
   *
   * @param authorization the {@link OAuth2Authorization}
   */
  @Override
  public void remove(OAuth2Authorization authorization) {}

  /**
   * Returns the {@link OAuth2Authorization} identified by the provided {@code id}, or {@code null}
   * if not found.
   *
   * @param id the authorization identifier
   * @return the {@link OAuth2Authorization} if found, otherwise {@code null}
   */
  @Override
  public OAuth2Authorization findById(String id) {
    return null;
  }

  /**
   * Returns the {@link OAuth2Authorization} containing the provided {@code token}, or {@code null}
   * if not found.
   *
   * @param token the token credential
   * @param tokenType the {@link OAuth2TokenType token type}
   * @return the {@link OAuth2Authorization} if found, otherwise {@code null}
   */
  @Override
  public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
    return null;
  }
}
