package com.xushifei.authorization.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xushifei.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客户端基本信息
 * </p>
 *
 * @author xushifei
 * @since 2021-12-24
 */
@Getter
@Setter
public class Client extends BaseEntity {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 重定向地址
     */
    private String redirectUri;

    /**
     * 是否为超管权限
     */
    @TableField("is_admin")
    private Boolean admin;

    /**
     * 客户端认证方式：client_secret_basic(http basic认证),client_secret_post(post提交认证),client_secret_jwt(jwt认证),private_key_jwt(私钥jwt认证),none(不认证),多个的情况以","分割
     */
    private String authenticationMethod;

    /**
     * 授权方式：authorization_code(授权码),refresh_token,client_credentials(客户端授权),password(密码授权),jwt_bearer
     */
    private String authorizationGrantType;

    /**
     * 是否校验key，0：否，1：是，默认否
     */
    @TableField("is_require_proof_key")
    private Boolean requireProofKey;

    /**
     * 是否自定义授权页面，0：否，1：是，默认否
     */
    @TableField("is_authorization_consent")
    private Boolean authorizationConsent;

    /**
     * access_token存活时间，单位为分钟，默认5分钟
     */
    private Integer accessTokenTimeToLive;

    /**
     * 返回access_token时是否重用refresh_token，0：否，1：是，默认1
     */
    @TableField("is_reuse_refresh_tokens")
    private Boolean reuseRefreshTokens;

    /**
     * refresh_token存活时间，单位为分钟，默认60分钟
     */
    private Integer refreshTokenTimeToLive;

    /**
     * 签名算法，RS256，RS384,RS512,ES256,ES384,ES512,PS256,PS384,PS512
     */
    private String signatureAlgorithm;


}
