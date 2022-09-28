package io.github.yunbamboos.util;

import java.util.Base64;

public class Base64Utils {

    private Base64Utils(){
    }

    /**
     * Base64编码器
     * @param str 待编码字符串
     * @return 编码后字符串
     * */
    public static String encode(String str) {
        byte [] strBytes = str.getBytes();
        return encode(strBytes);
    }

    /**
     * Base64编码器
     * @param bytes 待编码
     * @return 编码后字符串
     * */
    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64解码器
     * @param str 待解码字符串
     * @return 解码后字符串
     */
    public static String decode(String str) {
        byte [] base64Bytes = Base64.getDecoder().decode(str);
        return new String(base64Bytes);
    }

}
