package com.zwx;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Base64加解密demo
 *
 * @author 张文旭
 * @date 2021/01/06 14:38
 **/
public class Base64Demo {
    public static void main(String[] args) throws IOException {
        //此处仅仅为演示demo，这样写违反类单一职责原则,可以将编解码方法单独放置一个类中。
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        String data = "zhengzhou哈哈";
        //加密
        String encodeData = encoder.encodeBuffer(data.getBytes());
        System.out.println("加密结果: " + encodeData);
        //解密
        byte[] decodeData = decoder.decodeBuffer(encodeData);
        System.out.println("解密结果: " + new String(decodeData));
    }
}
