package com.github.yunbamboos.rest.converter;

import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.rest.http.HttpInputMessage;
import com.github.yunbamboos.rest.http.HttpOutputMessage;

/**
 * 用于从HTTP请求和响应进行转换和转换的策略接口
 */
public interface HttpMessageConverter {

    InDTO read(HttpInputMessage inputMessage);

    void write(OutDTO outDTO, HttpOutputMessage outputMessage);

}
