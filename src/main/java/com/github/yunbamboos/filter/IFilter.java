package com.github.yunbamboos.filter;

public interface IFilter extends Comparable<IFilter> {

    void filter(FilterExchange exchange, FilterChain chain);

    default int getOrder() {
        return 0;
    }

    default int compareTo(IFilter filter) {
        return Integer.compare(this.getOrder(), filter.getOrder());
    }
}