package com.zwx;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256
 *
 * @author 张文旭
 * @date 2021/01/06 14:56
 **/
public class Sha256Demo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final String data = "哈哈123okoko";
        //------------------java自带实现方式---------------
        System.out.println("------------------java自带实现方式---------------");
        String sha256StrJava = getSha256StrJava(data);
        System.out.println("java自带实现方式SHA256加密 = " + sha256StrJava);
        sha256StrJava = getSha256StrJava(data);
        System.out.println("java自带实现方式SHA256加密 = " + sha256StrJava);

        String sha256DataForApache = getSha256DataForApache(data);
        System.out.println("Apache实现方式SHA256加密 = " + sha256DataForApache);
    }

    /**
     * ------------------java自带实现方式---------------
     *
     * @param data
     * @return
     */
    public static String getSha256StrJava(String data) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(data.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * apache 工具
     *
     * @param data
     * @return
     */
    public static String getSha256DataForApache(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        MessageDigest messageDigest;
        String encdeStr = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }
}
