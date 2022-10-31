package io.github.yunbamboos.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.constant.FilterConst;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;

import static io.github.yunbamboos.constant.FilterExchangeConst.REQUEST_BODY_ATTR;

/**
 * 读取request请求内容
 */
public class ReadRequestFilter implements IFilter {

    @Override
    public void filter(FilterExchange exchange, FilterChain chain) {
        HttpServletRequest request = exchange.getRequest();
        String method = request.getMethod();
        if ("GET".equals(method)) {
            readGet(request).ifPresent(body -> exchange.getAttributes().put(REQUEST_BODY_ATTR, body));
        }
        if ("POST".equals(method)) {
            readPost(request).ifPresent(body -> exchange.getAttributes().put(REQUEST_BODY_ATTR, body));
        }
        chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterConst.READ_REQUEST_ORDER;
    }

    private Optional<JSONObject> readGet(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Iterator<String> it =  request.getParameterNames().asIterator();
        while (it.hasNext()){
            String param = it.next();
            String value = request.getParameter(param);
            json.put(param, value);
        }
        return Optional.of(json);
    }

    private Optional<JSONObject> readPost(HttpServletRequest request) {
        StringBuilder data = new StringBuilder();
        String line;
        BufferedReader reader;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            return Optional.empty();
        }
        if(data.length()>1){
            return Optional.of(JSON.parseObject(data.toString()));
        }
        return Optional.of(new JSONObject());
    }
}
