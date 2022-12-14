package io.github.yunbamboos.rest.converter;

import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.http.HttpInputMessage;
import io.github.yunbamboos.rest.http.HttpOutputMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class JSONHttpMessageConverter implements HttpMessageConverter{

    @Override
    public InDTO read(HttpInputMessage inputMessage) {
        return null;
    }

    @Override
    public void write(OutDTO outDTO, HttpOutputMessage outputMessage) {
        try {
            OutputStream out = outputMessage.getBody();
            out.write(outDTO.toJSON().getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
