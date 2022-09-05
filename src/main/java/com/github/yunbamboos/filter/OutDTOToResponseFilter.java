package com.github.yunbamboos.filter;

import com.github.yunbamboos.constant.ContentType;
import com.github.yunbamboos.constant.FilterConst;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.exception.AppException;
import com.github.yunbamboos.exception.ErrorCode;
import com.github.yunbamboos.rest.converter.JSONHttpMessageConverter;
import com.github.yunbamboos.rest.http.RestHttpOutputMessage;

import javax.servlet.http.HttpServletResponse;

import static com.github.yunbamboos.constant.FilterExchangeConst.OUT_DTO_ATTR;
import static com.github.yunbamboos.constant.FilterExchangeConst.REST_HTTP_OUTPUT_ATTR;
import static com.github.yunbamboos.constant.FilterExchangeConst.OUT_CONVERTER_ATTR;


/**
 * 将{@see IRestService}的出参{@see OutDTO} 转换Response
 */
public class OutDTOToResponseFilter implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        HttpServletResponse response = exchange.getResponse();
        OutDTO out = exchange.getAttribute(OUT_DTO_ATTR);
        if (out == null) {
            throw new AppException(ErrorCode.E500);
        }
        if(ContentType.APPLICATION_JSON == out.getContentType()){
            exchange.getAttributes().put(REST_HTTP_OUTPUT_ATTR, new RestHttpOutputMessage(response));
            exchange.getAttributes().put(OUT_CONVERTER_ATTR, new JSONHttpMessageConverter());
            response.setContentType(ContentType.APPLICATION_JSON.getContent());
        }
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConst.OUT_DTO_TO_RESPONSE_ORDER;
    }
}
