package com.xushifei.common.util;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * RSA加解密
 *
 * @author xushifei
 * @date 2022/2/16
 */
@Slf4j
public class RsaUtils {
  /** RSA实例化 */
  private static final String RSA_INSTANCE = "RSA";

  public static void main(String[] args) {
    KeyPair keyPair = generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();
    String message = "hello world";
    long start = System.currentTimeMillis();
    byte[] encrypt = encrypt(publicKey, message.getBytes(StandardCharsets.UTF_8));
    System.out.println("加密后报文:" + Base64.getEncoder().encodeToString(encrypt));
    byte[] decrypt = decrypt(privateKey, encrypt);
    System.out.println("解密后报文:" + new String(decrypt, StandardCharsets.UTF_8));
    System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * 默认生成1024长度秘钥对
   *
   * @return
   */
  public static KeyPair generateKeyPair() {
    return generateKeyPair(1024);
  }

  /**
   * 自定义key长度
   *
   * @param keySize
   * @return
   */
  public static KeyPair generateKeyPair(int keySize) {
    KeyPairGenerator kpGen = null;
    try {
      kpGen = KeyPairGenerator.getInstance("RSA");
    } catch (NoSuchAlgorithmException e) {
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "生成RSA秘钥失败");
    }
    kpGen.initialize(keySize);
    return kpGen.generateKeyPair();
  }
  /**
   * 公钥加密
   *
   * @param publicKey
   * @param message
   * @return
   */
  public static byte[] encrypt(PublicKey publicKey, byte[] message) {
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance(RSA_INSTANCE);
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      return cipher.doFinal(message);
    } catch (Exception e) {
      log.error("RSA加密失败:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "RSA加密失败");
    }
  }

  /**
   * 私钥解密
   *
   * @param privateKey
   * @param input
   * @return
   */
  public static byte[] decrypt(PrivateKey privateKey, byte[] input) {
    try {
      Cipher cipher = Cipher.getInstance(RSA_INSTANCE);
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      return cipher.doFinal(input);
    } catch (Exception e) {
      log.error("RSA解密失败:", e);
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "RSA解密失败");
    }
  }
}
