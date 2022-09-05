package com.github.yunbamboos.filter;

import com.github.yunbamboos.constant.FilterConst;
import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.ErrorOutDTO;
import com.github.yunbamboos.exception.AppException;
import com.github.yunbamboos.rest.IRestService;
import com.github.yunbamboos.rest.filter.DefaultRestServiceFilterExchange;
import com.github.yunbamboos.rest.filter.IRestServiceFilter;
import com.github.yunbamboos.rest.filter.IRestServiceFilterHandler;
import com.github.yunbamboos.rest.filter.IRestServiceFilterList;
import com.github.yunbamboos.rest.filter.RestServiceFilterExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.yunbamboos.constant.FilterExchangeConst.IN_DTO_ATTR;
import static com.github.yunbamboos.constant.FilterExchangeConst.OUT_DTO_ATTR;
import static com.github.yunbamboos.constant.FilterExchangeConst.REST_SERVICE_ATTR;

/**
 * 调用RestService过滤器
 */
public record InvokeRestServiceFilter(IRestServiceFilterHandler restServiceFilterHandler,
                                      IRestServiceFilterList restServiceFilterList) implements IFilter {

    private static final Logger log = LoggerFactory.getLogger(IFilter.class);

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        try {
            IRestService restService = exchange.getAttribute(REST_SERVICE_ATTR);
            if (restService == null) {
                throw new AppException("load rest service error");
            }
            InDTO in = exchange.getAttribute(IN_DTO_ATTR);
            RestServiceFilterExchange restServiceFilterExchange = new DefaultRestServiceFilterExchange();
            restServiceFilterExchange.in(in);
            restServiceFilterExchange.restService(restService);
            List<IRestServiceFilter> filters = restServiceFilterList.filters(restService.getFilters());
            restServiceFilterHandler.handle(restServiceFilterExchange, filters);
            exchange.getAttributes().put(OUT_DTO_ATTR, restServiceFilterExchange.out());
            chain.filter(exchange);
        } catch (Exception e) {
            log.error("invoke error", e);
            if (e instanceof AppException ae) {
                exchange.getAttributes().put(OUT_DTO_ATTR, new ErrorOutDTO(ae));
            } else {
                exchange.getAttributes().put(OUT_DTO_ATTR, new ErrorOutDTO(e));
            }
            chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return FilterConst.INVOKE_REST_SERVICE_ORDER;
    }
}