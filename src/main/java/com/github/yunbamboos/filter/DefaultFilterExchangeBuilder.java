package com.github.yunbamboos.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultFilterExchangeBuilder implements FilterExchange.Builder{

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public FilterExchange.Builder request(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public FilterExchange.Builder response(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    @Override
    public FilterExchange build() {
        return new DefaultFilterExchange(request, response);
    }
}
