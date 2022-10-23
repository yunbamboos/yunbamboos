package io.github.yunbamboos.filter;

import io.github.yunbamboos.constant.FilterConst;
import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.ErrorOutDTO;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.rest.filter.DefaultRestServiceFilterExchange;
import io.github.yunbamboos.rest.filter.IRestServiceFilter;
import io.github.yunbamboos.rest.filter.IRestServiceFilterHandler;
import io.github.yunbamboos.rest.filter.IRestServiceFilterList;
import io.github.yunbamboos.rest.filter.RestServiceFilterExchange;
import io.github.yunbamboos.transaction.RestServiceTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.github.yunbamboos.constant.FilterExchangeConst.IN_DTO_ATTR;
import static io.github.yunbamboos.constant.FilterExchangeConst.OUT_DTO_ATTR;
import static io.github.yunbamboos.constant.FilterExchangeConst.REST_SERVICE_ATTR;

/**
 * 调用RestService过滤器
 */
public record InvokeRestServiceFilter(IRestServiceFilterHandler restServiceFilterHandler,
                                      IRestServiceFilterList restServiceFilterList,
                                      RestServiceTransactionManager restServiceTransactionManager) implements IFilter {

    private static final Logger log = LoggerFactory.getLogger(IFilter.class);

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        IRestService restService = exchange.getAttribute(REST_SERVICE_ATTR);
        if (restService == null) {
            throw new AppException("load rest service error");
        }
        try {
            InDTO in = exchange.getAttribute(IN_DTO_ATTR);
            RestServiceFilterExchange restServiceFilterExchange = new DefaultRestServiceFilterExchange();
            restServiceFilterExchange.in(in);
            restServiceFilterExchange.restService(restService);
            restServiceTransactionManager.begin(restService); // 开启事务
            List<IRestServiceFilter> filters = restServiceFilterList.filters(restService.getFilters());
            restServiceFilterHandler.handle(restServiceFilterExchange, filters);
            exchange.getAttributes().put(OUT_DTO_ATTR, restServiceFilterExchange.out());
            chain.filter(exchange);
            restServiceTransactionManager.commit(restService); // 提交事务
        } catch (Exception e) {
            log.error("invoke error", e);
            restServiceTransactionManager.rollback(restService); // 事务回滚
            if (e instanceof AppException ae) {
                exchange.getAttributes().put(OUT_DTO_ATTR, new ErrorOutDTO(ae));
            } else {
                exchange.getAttributes().put(OUT_DTO_ATTR, new ErrorOutDTO(e));
            }
            chain.filter(exchange);
        } finally {
            restServiceTransactionManager.release(restService);
        }
    }

    @Override
    public int getOrder() {
        return FilterConst.INVOKE_REST_SERVICE_ORDER;
    }
}