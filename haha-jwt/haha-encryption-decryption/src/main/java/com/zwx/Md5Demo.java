package com.zwx;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * Md5加解密demo
 *
 * @author 张文旭
 * @date 2021/01/06 14:42
 **/
public class Md5Demo {
    public static void main(String[] args) {
        //SpringMD5工具
        String data = "哈哈123okok";
        String md5EncodeStr = SpringMd5.encodeMd5Hex(data);
        System.out.println("springM5D加密结果 = " + md5EncodeStr);
        // 匹配碰撞
        System.out.println("springM5D解密结果 = " + SpringMd5.verify(data, md5EncodeStr));
        System.out.println();

        //Java自带md5加密
        String s = EncryptUtil.md5(data);
        System.out.println("Java自带md5加密 = " + s);
        s = EncryptUtil.md5(data);
        System.out.println("Java自带md5加密 = " + s);
    }
}

class SpringMd5 {
    public static String encodeMd5Hex(String data) {
        if (data != null && data.length() > 0) {
            String encodedData = DigestUtils.md5DigestAsHex(data.getBytes());
            return encodedData;
        }
        return null;
    }


    public static boolean verify(String data, String encodedData) {
        if (data != null && data.length() > 0 && encodedData != null && encodedData.length() > 0) {
            return encodedData.equals(DigestUtils.md5DigestAsHex(data.getBytes()));
        }
        return false;
    }
}

class EncryptUtil {
    /**
     * MD5加密
     *
     * @param jiami 源字符串
     * @return 加密后的字符串
     */
    public final static String md5(String jiami) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = jiami.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EncryptUtil() {
        // 禁止实例化
    }
}
