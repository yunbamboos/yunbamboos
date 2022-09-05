package io.github.yunbamboos.rest.filter;

public interface RestServiceFilterChain {

    void filter(RestServiceFilterExchange exchange);

}
