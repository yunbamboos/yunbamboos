package io.github.yunbamboos.filter;

import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultFilterExchange implements FilterExchange {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final Map<String, Object> attributes;

    DefaultFilterExchange(HttpServletRequest request, HttpServletResponse response) {
        this(request, response, new ConcurrentHashMap<>(100));
    }

    DefaultFilterExchange(HttpServletRequest request, HttpServletResponse response, Map<String, Object> attributes) {
        Assert.notNull(request, "'request' is required");
        Assert.notNull(response, "'response' is required");
        Assert.notNull(attributes, "'attributes' is required");
        this.request = request;
        this.response = response;
        this.attributes = attributes;
    }

    @Override
    public HttpServletRequest getRequest() {
        return this.request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return this.response;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}
