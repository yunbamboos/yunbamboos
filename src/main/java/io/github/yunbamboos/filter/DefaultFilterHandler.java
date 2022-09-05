package io.github.yunbamboos.filter;

import java.util.List;

public record DefaultFilterHandler(List<IFilter> filters) implements IFilterHandler {

    @Override
    public void handle(FilterExchange exchange) {
        new DefaultFilterChain(filters).filter(exchange);
    }

}
