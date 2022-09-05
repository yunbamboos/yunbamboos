package com.github.yunbamboos.util;

/**
 * 字符串工具栏
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 判断给定的字符串是否为null或者是空的
     *
     * @param string 给定的字符串
     * @return 字符串 null 或者 "" 为true
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    /**
     * 判断给定的字符串是否不为null且不为空
     *
     * @param string 给定的字符串
     * @return 判断给定的字符串是否不为null且不为空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 将对象转化字符串
     * @param obj 待转化对象
     * @return 字符串
     * */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}
