package com.encrypt.demo.service;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AES256Service {

  public static String encrypt(String strToEncrypt, String secret) {
    try {
      var secretKey = getSecretKey(hashKey(secret));
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
      throw new RuntimeException("encrypt error", e);
    }
  }

  public static String decrypt(String strToDecrypt, String secret) {
    try {
      var secretKey = getSecretKey(hashKey(secret));
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      throw new RuntimeException("decrypt error", e);
    }
  }

  public static SecretKeySpec getSecretKey(String myKey) {
    try {
      byte[] key = myKey.getBytes("UTF-8");
      key = Arrays.copyOf(key, 32);
      // log.debug("key Hex=[" + encodeHexString(key) + "]");

      return new SecretKeySpec(key, "AES");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("setKey error", e);
    }
  }

  public static String hashKey(String myKey) {
    MessageDigest sha = null;
    try {
      byte[] key = myKey.getBytes("UTF-8");
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 32);
      // log.debug("encodeHexString(key)=[" + encodeHexString(key) + "]");

      return encodeHexString(key);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("NoSuchAlgorithmException error", e);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("UnsupportedEncodingException error", e);
    }
  }

  private static String encodeHexString(byte[] byteArray) {
    StringBuffer hexStringBuffer = new StringBuffer();
    for (int i = 0; i < byteArray.length; i++) {
      hexStringBuffer.append(byteToHex(byteArray[i]));
    }
    return hexStringBuffer.toString();
  }

  private static String byteToHex(byte num) {
    char[] hexDigits = new char[2];
    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
    hexDigits[1] = Character.forDigit((num & 0xF), 16);
    return new String(hexDigits);
  }

  public static void main(String[] args) {
    // String originalString = "test1357";
    //
    // String encryptedString = AES256Service.encrypt(originalString);
    // String decryptedString = AES256Service.decrypt(encryptedString);
    //
    // log.debug(originalString);
    // log.debug(encryptedString);
    // log.debug(decryptedString);

    final String secretKey = "secretKey";

    String originalString = "password";
    String encryptedString = AES256Service.encrypt(originalString, secretKey);
    String decryptedString = AES256Service.decrypt(encryptedString, secretKey);

    log.debug(originalString);
    log.debug(encryptedString);
    log.debug(decryptedString);
  }
}
