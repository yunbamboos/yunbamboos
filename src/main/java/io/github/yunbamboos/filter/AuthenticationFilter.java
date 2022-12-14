package io.github.yunbamboos.filter;

import io.github.yunbamboos.constant.FilterConst;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.exception.ErrorCode;
import io.github.yunbamboos.listener.AuthenticationListener;
import io.github.yunbamboos.listener.event.AuthenticationEvent;
import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.util.CollectionUtils;

import java.util.List;

import static io.github.yunbamboos.constant.FilterExchangeConst.REST_SERVICE_ATTR;

/**
 * 认证服务过滤器
 */
public record AuthenticationFilter(List<AuthenticationListener> authenticationListenerList) implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        IRestService restService = exchange.getAttribute(REST_SERVICE_ATTR);
        if (restService == null) {
            throw new AppException("load rest service error");
        }
        if (restService.isAuthentication()) {
            if (CollectionUtils.isNotEmpty(authenticationListenerList)) {
                AuthenticationEvent event = new AuthenticationEvent(exchange.getRequest());
                authenticationListenerList.forEach(authenticationListener -> {
                    if (!authenticationListener.auth(event)) {
                        throw new AppException(ErrorCode.E401);
                    }
                });
                chain.filter(exchange);
            } else {
                throw new AppException(ErrorCode.E401);
            }
        } else {
            chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return FilterConst.AUTHENTICATION_ORDER;
    }
}
