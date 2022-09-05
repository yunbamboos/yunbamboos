package io.github.yunbamboos.rest.converter;

import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.http.HttpInputMessage;
import io.github.yunbamboos.rest.http.HttpOutputMessage;

/**
 * 用于从HTTP请求和响应进行转换和转换的策略接口
 */
public interface HttpMessageConverter {

    InDTO read(HttpInputMessage inputMessage);

    void write(OutDTO outDTO, HttpOutputMessage outputMessage);

}
