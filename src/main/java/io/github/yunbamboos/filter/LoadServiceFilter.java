package io.github.yunbamboos.filter;

import io.github.yunbamboos.constant.FilterConst;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.exception.ErrorCode;
import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.rest.IRestServiceList;


import javax.servlet.http.HttpServletRequest;

import static io.github.yunbamboos.constant.FilterExchangeConst.REST_SERVICE_ATTR;

/**
 * 加载服务过滤器
 */
public record LoadServiceFilter(IRestServiceList restServiceList) implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        if (restServiceList == null) {
            throw new AppException(ErrorCode.E403);
        }
        String contextPath = exchange.getContextPath();
        String uri = exchange.getRequestURI();
        String url = uri.replaceAll(contextPath, "");
        IRestService restService = restServiceList.get(url).orElseThrow(() -> {
            throw new AppException(ErrorCode.E404);
        });
        exchange.getAttributes().put(REST_SERVICE_ATTR, restService);
        String method = restService.getRequestMethod().toString();
        HttpServletRequest request = exchange.getRequest();
        if (method.equals(request.getMethod())) {
            chain.filter(exchange);
        } else {
            throw new AppException(ErrorCode.E405);
        }
    }

    @Override
    public int getOrder() {
        return FilterConst.LOAD_SERVICE_ORDER;
    }
}
