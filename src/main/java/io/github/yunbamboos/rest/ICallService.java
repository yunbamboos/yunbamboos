package io.github.yunbamboos.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调用服务接口
 * */
public interface ICallService {

    /**
     * 解析请求路径调用对应服务并且将返回结果写入响应
     * @param request 请求
     * @param response 响应请求 */
    void call(HttpServletRequest request, HttpServletResponse response);
}
