package io.github.yunbamboos.filter;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 过滤器交换
 */
public interface FilterExchange {

    /**
     * 返回当前 http请求
     */
    HttpServletRequest getRequest();

    /**
     * 返回当前 http响应
     */
    HttpServletResponse getResponse();

    /**
     * 返回当前交换的请求属性的可变映射
     */
    Map<String, Object> getAttributes();

    @SuppressWarnings("unchecked")
    @Nullable
    default <T> T getAttribute(String name) {
        return (T) getAttributes().get(name);
    }

    default <T> T getRequiredAttribute(String name) {
        T value = getAttribute(name);
        Assert.notNull(value, () -> "Required attribute '" + name + "' is missing");
        return value;
    }

    @SuppressWarnings("unchecked")
    default <T> T getAttributeOrDefault(String name, T defaultValue) {
        return (T) getAttributes().getOrDefault(name, defaultValue);
    }

    default String getContextPath() {
        return getRequest().getContextPath();
    }

    default String getRequestURI() {
        return getRequest().getRequestURI();
    }

    interface Builder {

        Builder request(HttpServletRequest request);

        Builder response(HttpServletResponse response);

        FilterExchange build();
    }

}
