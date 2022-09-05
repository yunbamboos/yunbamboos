package io.github.yunbamboos.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * 程序启动监听
 */
public interface AppStartListener extends Ordered {
    @Override
    default int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 监听启动执行事件
     * @param event 启动事件
     */
    void onApplicationEvent(ContextRefreshedEvent event);
}
