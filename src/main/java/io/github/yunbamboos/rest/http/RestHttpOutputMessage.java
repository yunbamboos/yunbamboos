package io.github.yunbamboos.rest.http;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class RestHttpOutputMessage implements HttpOutputMessage{

    private final HttpServletResponse response;

    public RestHttpOutputMessage(HttpServletResponse response){
        this.response = response;
    }

    @Override
    public OutputStream getBody() throws IOException {
        return response.getOutputStream();
    }
}
