package com.github.yunbamboos.filter;

import com.github.yunbamboos.constant.FilterConst;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.exception.AppException;
import com.github.yunbamboos.exception.ErrorCode;
import com.github.yunbamboos.rest.converter.HttpMessageConverter;
import com.github.yunbamboos.rest.http.HttpOutputMessage;

import static com.github.yunbamboos.constant.FilterExchangeConst.OUT_DTO_ATTR;
import static com.github.yunbamboos.constant.FilterExchangeConst.REST_HTTP_OUTPUT_ATTR;
import static com.github.yunbamboos.constant.FilterExchangeConst.OUT_CONVERTER_ATTR;

/**
 * 写response过滤器
 */
public class WriteResponseFilter implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        OutDTO out = exchange.getAttribute(OUT_DTO_ATTR);
        HttpOutputMessage httpOutputMessage = exchange.getAttribute(REST_HTTP_OUTPUT_ATTR);
        HttpMessageConverter converter = exchange.getAttribute(OUT_CONVERTER_ATTR);
        if (out == null || httpOutputMessage == null || converter == null) {
            throw new AppException(ErrorCode.E500);
        }
        converter.write(out, httpOutputMessage);
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConst.WRITE_RESPONSE_ORDER;
    }
}