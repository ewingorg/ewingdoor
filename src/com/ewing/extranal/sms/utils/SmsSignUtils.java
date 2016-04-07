package com.ewing.extranal.sms.utils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.ewing.extranal.sms.constants.Constants;
import com.sun.star.io.IOException;

/**
 * 短信平台接口加密工具类
 * 
 * @author Joeson Chan<chenxuegui.cxg@1234@163.com>
 * @since 2016年4月7日
 *
 */
public class SmsSignUtils {

  public static String signTopRequest(Map<String, String> params, String secret, String signMethod)
      throws IOException {
    // 第一步：检查参数是否已经排序
    String[] keys = params.keySet().toArray(new String[0]);
    Arrays.sort(keys);

    // 第二步：把所有参数名和参数值串在一起
    StringBuilder query = new StringBuilder();
    if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
      query.append(secret);
    }
    for (String key : keys) {
      String value = params.get(key);
      if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
        query.append(key).append(value);
      }
    }

    // 第三步：使用MD5/HMAC加密
    byte[] bytes;
    if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
      bytes = encryptHMAC(query.toString(), secret);
    } else {
      query.append(secret);
      bytes = encryptMD5(query.toString());
    }

    // 第四步：把二进制转化为大写的十六进制
    return byte2hex(bytes);
  }

  public static byte[] encryptHMAC(String data, String secret) throws IOException {
    byte[] bytes = null;
    try {
      SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
      Mac mac = Mac.getInstance(secretKey.getAlgorithm());
      mac.init(secretKey);
      bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
    } catch (GeneralSecurityException gse) {
      throw new IOException(gse.toString());
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return bytes;
  }

  public static byte[] encryptMD5(String data) {
    try {
      return DigestUtils.md5(data.getBytes(Constants.CHARSET_UTF8));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    // return encryptMD5(data.getBytes(Constants.CHARSET_UTF8));
    return null;
  }

  public static String byte2hex(byte[] bytes) {
    StringBuilder sign = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      String hex = Integer.toHexString(bytes[i] & 0xFF);
      if (hex.length() == 1) {
        sign.append("0");
      }
      sign.append(hex.toUpperCase());
    }
    return sign.toString();
  }

}
