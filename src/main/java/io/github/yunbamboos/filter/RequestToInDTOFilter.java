package io.github.yunbamboos.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.constant.FilterConst;
import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.rest.IRestService;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static io.github.yunbamboos.constant.FilterExchangeConst.REST_SERVICE_ATTR;
import static io.github.yunbamboos.constant.FilterExchangeConst.REQUEST_BODY_ATTR;
import static io.github.yunbamboos.constant.FilterExchangeConst.IN_DTO_ATTR;

/**
 * 将request转换{@see IRestService}的入参{@see InDTO}
 */
public class RequestToInDTOFilter implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        IRestService restService = exchange.getAttribute(REST_SERVICE_ATTR);
        if (restService == null) {
            throw new AppException("load rest service error");
        }
        InDTO in = create(restService.getIn()).orElseThrow(() -> {
            throw new AppException("request to dto fail");
        });
        String body = exchange.getAttribute(REQUEST_BODY_ATTR);
        JSONObject json = JSON.parseObject(body);
        in.decode(json);
        exchange.getAttributes().put(IN_DTO_ATTR, in);
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConst.REQUEST_TO_IN_DTO_ORDER;
    }

    public static Optional<InDTO> create(Class<? extends InDTO> inClass) {
        try {
            return Optional.of(inClass.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
