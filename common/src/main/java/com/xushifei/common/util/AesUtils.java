package com.xushifei.common.util;

import com.xushifei.common.enums.ApiCodeEnum;
import com.xushifei.common.exception.BusinessException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加解密工具类
 *
 * @author xushifei
 * @date 2022/2/16
 */
public class AesUtils {
  /** 加密方法 */
  private static final String ALGORITHM = "AES";
  /** ECB加密 */
  private static final String CIPHER_ECB = "AES/ECB/PKCS5Padding";
  /** CBC加密 */
  private static final String CIPHER_CBC = "AES/CBC/PKCS5Padding";

  /**
   * 生成秘钥
   *
   * @return
   */
  public static byte[] generateKey() {
    KeyGenerator keyGenerator = null;
    try {
      keyGenerator = KeyGenerator.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      throw new BusinessException(ApiCodeEnum.SYSTEM_ERROR.getCode(), "生成AES秘钥失败");
    }
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();
    return secretKey.getEncoded();
  }

  /**
   * CBC加密
   *
   * @param key
   * @param input
   * @return
   * @throws GeneralSecurityException
   */
  public static byte[] cbcEncrypt(byte[] key, byte[] input) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(CIPHER_CBC);
    SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
    // CBC模式需要生成一个16 bytes的initialization vector:
    SecureRandom sr = SecureRandom.getInstanceStrong();
    byte[] iv = sr.generateSeed(16);
    IvParameterSpec ivps = new IvParameterSpec(iv);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
    byte[] data = cipher.doFinal(input);
    // IV不需要保密，把IV和密文一起返回:
    return join(iv, data);
  }

  /**
   * CBC解密
   *
   * @param key
   * @param input
   * @return
   * @throws GeneralSecurityException
   */
  public static byte[] cbcDecrypt(byte[] key, byte[] input) throws GeneralSecurityException {
    // 把input分割成IV和密文:
    byte[] iv = new byte[16];
    byte[] data = new byte[input.length - 16];
    System.arraycopy(input, 0, iv, 0, 16);
    System.arraycopy(input, 16, data, 0, data.length);
    // 解密:
    Cipher cipher = Cipher.getInstance(CIPHER_CBC);
    SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
    IvParameterSpec ivps = new IvParameterSpec(iv);
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
    return cipher.doFinal(data);
  }

  /**
   * 数组合并,bs1在前，bs2在后
   *
   * @param bs1
   * @param bs2
   * @return
   */
  private static byte[] join(byte[] bs1, byte[] bs2) {
    byte[] r = new byte[bs1.length + bs2.length];
    System.arraycopy(bs1, 0, r, 0, bs1.length);
    System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
    return r;
  }

  /**
   * ECB加密 ECB模式是最简单的AES加密模式，它只需要一个固定长度的密钥，固定的明文会生成固定的密文，这种一对一的加密方式会导致安全性降低
   *
   * @param key 秘钥
   * @param input 明文对应的byte数组
   * @return
   * @throws GeneralSecurityException
   */
  public static byte[] ecbEncrypt(byte[] key, byte[] input) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(CIPHER_ECB);
    SecretKey keySpec = new SecretKeySpec(key, ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    return cipher.doFinal(input);
  }

  /**
   * ECB解密
   *
   * @param key
   * @param input
   * @return
   * @throws GeneralSecurityException
   */
  public static byte[] ecbDecrypt(byte[] key, byte[] input) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(CIPHER_ECB);
    SecretKey keySpec = new SecretKeySpec(key, ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    return cipher.doFinal(input);
  }
}
