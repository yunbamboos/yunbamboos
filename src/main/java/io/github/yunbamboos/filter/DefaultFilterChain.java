package io.github.yunbamboos.filter;

import java.util.List;

public class DefaultFilterChain implements FilterChain {

    private final int index;

    private final List<IFilter> filters;

    public DefaultFilterChain(List<IFilter> filters) {
        this.index = 0;
        this.filters = filters;
    }

    public DefaultFilterChain(int index, List<IFilter> filters) {
        this.index = index;
        this.filters = filters;
    }

    private DefaultFilterChain(int index, DefaultFilterChain parent) {
        this.index = index;
        this.filters = parent.filters;
    }

    @Override
    public void filter(FilterExchange exchange) {
        if (this.index < filters.size()) {
            IFilter filter = filters.get(this.index);
            DefaultFilterChain chain = new DefaultFilterChain(this.index + 1, this);
            filter.filter(exchange, chain);
        }
    }
}
