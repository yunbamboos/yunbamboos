package io.github.yunbamboos.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Object 对象处理工具
 */
public class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * 判断对象是否为null
     *
     * @param obj 判断对象
     * @return 对象是null为true
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 判断对象是否为null
     *
     * @param obj 判断对象
     * @return 对象是不是null为true
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 转换List的泛型指定类{@code T}
     *
     * @param obj   转换对象
     * @param clazz 转换类型
     * @return 转换后列表
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return Collections.emptyList();
    }


}
