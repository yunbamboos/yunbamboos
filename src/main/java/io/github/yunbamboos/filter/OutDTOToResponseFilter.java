package io.github.yunbamboos.filter;

import io.github.yunbamboos.constant.ContentType;
import io.github.yunbamboos.constant.FilterConst;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.exception.ErrorCode;
import io.github.yunbamboos.rest.converter.ByteHttpMessageConverter;
import io.github.yunbamboos.rest.converter.JSONHttpMessageConverter;
import io.github.yunbamboos.rest.http.RestHttpOutputMessage;

import javax.servlet.http.HttpServletResponse;

import static io.github.yunbamboos.constant.FilterExchangeConst.OUT_DTO_ATTR;
import static io.github.yunbamboos.constant.FilterExchangeConst.REST_HTTP_OUTPUT_ATTR;
import static io.github.yunbamboos.constant.FilterExchangeConst.OUT_CONVERTER_ATTR;


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
        if(ContentType.APPLICATION_IMAGE == out.getContentType()){
            exchange.getAttributes().put(REST_HTTP_OUTPUT_ATTR, new RestHttpOutputMessage(response));
            exchange.getAttributes().put(OUT_CONVERTER_ATTR, new ByteHttpMessageConverter());
            response.setContentType(ContentType.APPLICATION_IMAGE.getContent());
        }
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConst.OUT_DTO_TO_RESPONSE_ORDER;
    }
}
