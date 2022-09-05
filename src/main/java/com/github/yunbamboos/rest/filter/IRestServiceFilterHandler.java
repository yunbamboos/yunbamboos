package com.github.yunbamboos.rest.filter;

import java.util.List;

public interface IRestServiceFilterHandler {

    void handle(RestServiceFilterExchange exchange, List<IRestServiceFilter> filters);

}
