package com.xushifei.authorization.server.service;

import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xushifei
 * @date 2021/12/6
 */
public class InMemoryOAuth2AuthorizationService implements OAuth2AuthorizationService {
  private final Map<String, OAuth2Authorization> authorizations = new ConcurrentHashMap<>();

  /** Constructs an {@code InMemoryOAuth2AuthorizationService}. */
  public InMemoryOAuth2AuthorizationService() {
    this(Collections.emptyList());
  }

  /**
   * Constructs an {@code InMemoryOAuth2AuthorizationService} using the provided parameters.
   *
   * @param authorizations the authorization(s)
   */
  public InMemoryOAuth2AuthorizationService(OAuth2Authorization... authorizations) {
    this(Arrays.asList(authorizations));
  }

  /**
   * Constructs an {@code InMemoryOAuth2AuthorizationService} using the provided parameters.
   *
   * @param authorizations the authorization(s)
   */
  public InMemoryOAuth2AuthorizationService(List<OAuth2Authorization> authorizations) {
    Assert.notNull(authorizations, "authorizations cannot be null");
    authorizations.forEach(
        authorization -> {
          Assert.notNull(authorization, "authorization cannot be null");
          Assert.isTrue(
              !this.authorizations.containsKey(authorization.getId()),
              "The authorization must be unique. Found duplicate identifier: "
                  + authorization.getId());
          this.authorizations.put(authorization.getId(), authorization);
        });
  }

  @Override
  public void save(OAuth2Authorization authorization) {
    Assert.notNull(authorization, "authorization cannot be null");
    this.authorizations.put(authorization.getId(), authorization);
  }

  @Override
  public void remove(OAuth2Authorization authorization) {
    Assert.notNull(authorization, "authorization cannot be null");
    this.authorizations.remove(authorization.getId(), authorization);
  }

  @Nullable
  @Override
  public OAuth2Authorization findById(String id) {
    Assert.hasText(id, "id cannot be empty");
    return this.authorizations.get(id);
  }

  @Nullable
  @Override
  public OAuth2Authorization findByToken(String token, @Nullable OAuth2TokenType tokenType) {
    Assert.hasText(token, "token cannot be empty");
    for (OAuth2Authorization authorization : this.authorizations.values()) {
      if (hasToken(authorization, token, tokenType)) {
        return authorization;
      }
    }
    return null;
  }

  private static boolean hasToken(
      OAuth2Authorization authorization, String token, @Nullable OAuth2TokenType tokenType) {
    if (tokenType == null) {
      return matchesState(authorization, token)
          || matchesAuthorizationCode(authorization, token)
          || matchesAccessToken(authorization, token)
          || matchesRefreshToken(authorization, token);
    } else if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
      return matchesState(authorization, token);
    } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
      return matchesAuthorizationCode(authorization, token);
    } else if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {
      return matchesAccessToken(authorization, token);
    } else if (OAuth2TokenType.REFRESH_TOKEN.equals(tokenType)) {
      return matchesRefreshToken(authorization, token);
    }
    return false;
  }

  private static boolean matchesState(OAuth2Authorization authorization, String token) {
    return token.equals(authorization.getAttribute(OAuth2ParameterNames.STATE));
  }

  private static boolean matchesAuthorizationCode(OAuth2Authorization authorization, String token) {
    OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
        authorization.getToken(OAuth2AuthorizationCode.class);
    return authorizationCode != null && authorizationCode.getToken().getTokenValue().equals(token);
  }

  private static boolean matchesAccessToken(OAuth2Authorization authorization, String token) {
    OAuth2Authorization.Token<OAuth2AccessToken> accessToken =
        authorization.getToken(OAuth2AccessToken.class);
    return accessToken != null && accessToken.getToken().getTokenValue().equals(token);
  }

  private static boolean matchesRefreshToken(OAuth2Authorization authorization, String token) {
    OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken =
        authorization.getToken(OAuth2RefreshToken.class);
    return refreshToken != null && refreshToken.getToken().getTokenValue().equals(token);
  }
}
