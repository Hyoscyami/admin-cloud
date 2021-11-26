package com.xushifei.authorization.config;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * client持久化
 *
 * @author xushifei
 * @date 2021/11/26
 */
public class JdbcRegisteredClientRepository implements RegisteredClientRepository {
  /**
   * Saves the registered client.
   *
   * @param registeredClient the {@link RegisteredClient}
   */
  @Override
  public void save(RegisteredClient registeredClient) {}

  /**
   * Returns the registered client identified by the provided {@code id}, or {@code null} if not
   * found.
   *
   * @param id the registration identifier
   * @return the {@link RegisteredClient} if found, otherwise {@code null}
   */
  @Override
  public RegisteredClient findById(String id) {
    return null;
  }

  /**
   * Returns the registered client identified by the provided {@code clientId}, or {@code null} if
   * not found.
   *
   * @param clientId the client identifier
   * @return the {@link RegisteredClient} if found, otherwise {@code null}
   */
  @Override
  public RegisteredClient findByClientId(String clientId) {
    return null;
  }
}
