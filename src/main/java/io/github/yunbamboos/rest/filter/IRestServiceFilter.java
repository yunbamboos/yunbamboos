package io.github.yunbamboos.rest.filter;

public interface IRestServiceFilter extends Comparable<IRestServiceFilter> {

    void filter(RestServiceFilterExchange exchange, RestServiceFilterChain chain);

    default int getOrder() {
        return 0;
    }

    default int compareTo(IRestServiceFilter filter) {
        return Integer.compare(this.getOrder(), filter.getOrder());
    }

}
