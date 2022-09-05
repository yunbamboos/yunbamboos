package com.github.yunbamboos.rest.filter;

import java.util.List;

public class DefaultRestServiceFilterChain implements RestServiceFilterChain{

    private final int index;

    private final List<IRestServiceFilter> filters;

    public DefaultRestServiceFilterChain(List<IRestServiceFilter> filters) {
        this.index = 0;
        this.filters = filters;
    }

    public DefaultRestServiceFilterChain(int index, List<IRestServiceFilter> filters) {
        this.index = index;
        this.filters = filters;
    }

    private DefaultRestServiceFilterChain(int index, DefaultRestServiceFilterChain parent) {
        this.index = index;
        this.filters = parent.filters;
    }

    @Override
    public void filter(RestServiceFilterExchange exchange) {
        if (this.index < filters.size()) {
            IRestServiceFilter filter = filters.get(this.index);
            DefaultRestServiceFilterChain chain = new DefaultRestServiceFilterChain(this.index + 1, this);
            filter.filter(exchange, chain);
        }
    }
}
