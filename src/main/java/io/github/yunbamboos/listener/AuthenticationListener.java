package io.github.yunbamboos.listener;

import io.github.yunbamboos.listener.event.AuthenticationEvent;

/**
 * 权限认证监听
 */
public interface AuthenticationListener {

    /**
     * 权限认证
     * @param event 认证事件{@link AuthenticationEvent}
     * @return 认证成功{@code true} 失败{@code false}
     * */
    boolean auth(AuthenticationEvent event);
}
