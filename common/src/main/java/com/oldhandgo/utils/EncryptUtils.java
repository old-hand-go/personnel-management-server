package com.oldhandgo.utils;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

/**
 * 加密工具类
 *
 * @author dormirr
 */
public class EncryptUtils {
    private static String strKey = "Passw0rd", strParam = "Passw0rd";

    /**
     * 对称加密
     *
     * @param source 密码
     * @return 加密密码
     * @throws Exception
     */
    public static String desEncrypt(String source) throws Exception {
        if (source == null || source.length() == 0) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(strParam.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return byte2hex(
                cipher.doFinal(source.getBytes(StandardCharsets.UTF_8))).toUpperCase();
    }

    /**
     * 二进制转十六进制
     *
     * @param inStr 二进制
     * @return 十六进制
     */
    public static String byte2hex(byte[] inStr) {
        String stmp;
        StringBuffer out = new StringBuffer(inStr.length * 2);
        for (byte b : inStr) {
            stmp = Integer.toHexString(b & 0xFF);
            if (stmp.length() == 1) {
                // 如果是0至F的单位字符串，则添加0
                out.append("0").append(stmp);
            } else {
                out.append(stmp);
            }
        }
        return out.toString();
    }

    /**
     * 十六进制转二进制
     *
     * @param b 十六进制
     * @return 二进制
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 对称解密
     *
     * @param source 加密密码
     * @return 密码
     * @throws Exception
     */
    public static String desDecrypt(String source) throws Exception {
        if (source == null || source.length() == 0) {
            return null;
        }
        byte[] src = hex2byte(source.getBytes());
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(strParam.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(src);
        return new String(retByte);
    }

    /**
     * 密码加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
