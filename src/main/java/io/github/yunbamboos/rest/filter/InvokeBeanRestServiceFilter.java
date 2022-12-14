package io.github.yunbamboos.rest.filter;

import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.rest.proxy.InvokeService;

/**
 * 调用具体bean服务
 */
public class InvokeBeanRestServiceFilter implements IRestServiceFilter {

    @Override
    public void filter(RestServiceFilterExchange exchange, RestServiceFilterChain chain) {
        IRestService restService = exchange.restService();
        InvokeService invokeService = restService.getInvoke().orElseThrow(() -> {
            throw new AppException("invoke service error");
        });
        OutDTO out = invokeService.invoke(restService.getBean(), exchange.in()).orElseThrow(() -> {
            throw new AppException("rest service return is null");
        });
        exchange.out(out);
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
