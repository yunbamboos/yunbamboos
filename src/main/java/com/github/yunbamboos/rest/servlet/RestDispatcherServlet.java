package com.github.yunbamboos.rest.servlet;

import com.github.yunbamboos.rest.ICallService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * rest调度服务器
 */
@RestController
public class RestDispatcherServlet {

    @Resource
    private ICallService callService;

    @RequestMapping(value = "/**")
    public void rest(HttpServletRequest request, HttpServletResponse response) {
        callService.call(request, response);
    }
}
