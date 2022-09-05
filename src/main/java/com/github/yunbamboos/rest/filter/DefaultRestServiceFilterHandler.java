package com.github.yunbamboos.rest.filter;

import java.util.List;

public class DefaultRestServiceFilterHandler implements IRestServiceFilterHandler {

    @Override
    public void handle(RestServiceFilterExchange exchange, List<IRestServiceFilter> filters) {
        new DefaultRestServiceFilterChain(filters).filter(exchange);
    }

}
