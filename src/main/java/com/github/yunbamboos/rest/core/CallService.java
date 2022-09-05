package com.github.yunbamboos.rest.core;

import com.alibaba.fastjson.JSONObject;
import com.github.yunbamboos.exception.AppException;
import com.github.yunbamboos.filter.DefaultFilterExchangeBuilder;
import com.github.yunbamboos.filter.DefaultFilterHandler;
import com.github.yunbamboos.filter.FilterExchange;
import com.github.yunbamboos.filter.IFilterList;
import com.github.yunbamboos.rest.ICallService;
import com.github.yunbamboos.rest.IRestServiceList;
import com.github.yunbamboos.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public record CallService(IFilterList filterList, IRestServiceList restServiceList) implements ICallService {

    private static final Logger log = LoggerFactory.getLogger(ICallService.class);

    @Override
    public void call(HttpServletRequest request, HttpServletResponse response) {
        log.info("start call server {}", request.getRequestURI());
        long start = System.currentTimeMillis();
        try {
            FilterExchange exchange = new DefaultFilterExchangeBuilder().request(request).response(response).build();
            new DefaultFilterHandler(filterList.getFilters()).handle(exchange);
        } catch (Exception e) {
            log.error("call server " + request.getRequestURI(), e);
            writeErrorResponse(e, response);
        } finally {
            SessionContext.clean();
        }
        log.info("end call server {} {}", request.getRequestURI(), (System.currentTimeMillis() - start));
    }

    private void writeErrorResponse(Exception exception, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            JSONObject json = new JSONObject();
            if (exception instanceof AppException ae) {
                json.put("code", ae.getCode());
                json.put("msg", ae.getMsg());
            } else {
                json.put("code", 500);
                json.put("msg", exception.getMessage());
            }
            writer.write(json.toJSONString());
            writer.flush();
        } catch (IOException e) {
            log.error("writer error", e);
        }
    }

}
