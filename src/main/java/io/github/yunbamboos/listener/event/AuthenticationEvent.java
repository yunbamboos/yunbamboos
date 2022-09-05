package io.github.yunbamboos.listener.event;

import javax.servlet.http.HttpServletRequest;

public record AuthenticationEvent(HttpServletRequest request) {

    public HttpServletRequest getRequest() {
        return request;
    }
}
