package io.github.yunbamboos.filter;

import io.github.yunbamboos.constant.FilterConst;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.exception.ErrorCode;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求方法过滤器
 */
public class HttpRequestMethodFilter implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        HttpServletRequest request = exchange.getRequest();
        String method = request.getMethod();
        if ("GET".equals(method) || "POST".equals(method)) {
            chain.filter(exchange);
        } else {
            throw new AppException(ErrorCode.E405);
        }
    }

    @Override
    public int getOrder() {
        return FilterConst.HTTP_REQUEST_METHOD_ORDER;
    }
}
