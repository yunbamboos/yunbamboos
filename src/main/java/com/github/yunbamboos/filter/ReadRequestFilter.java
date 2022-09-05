package com.github.yunbamboos.filter;

import com.github.yunbamboos.constant.FilterConst;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import static com.github.yunbamboos.constant.FilterExchangeConst.REQUEST_BODY_ATTR;

/**
 * 读取request请求内容
 */
public class ReadRequestFilter implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        HttpServletRequest request = exchange.getRequest();
        String method = request.getMethod();
        if ("GET".equals(method)) {
            readGet(request).ifPresent(body -> exchange.getAttributes().put(REQUEST_BODY_ATTR, body));
        }
        if ("POST".equals(method)) {
            readPost(request).ifPresent(body -> exchange.getAttributes().put(REQUEST_BODY_ATTR, body));
        }
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConst.READ_REQUEST_ORDER;
    }

    private Optional<String> readGet(HttpServletRequest request) {
        return Optional.ofNullable(request.getParameter("json"));
    }

    private Optional<String> readPost(HttpServletRequest request) {
        StringBuilder data = new StringBuilder();
        String line;
        BufferedReader reader;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(data.toString());
    }
}
