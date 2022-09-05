package io.github.yunbamboos.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;

/**
 * 程序关闭监听
 */
public interface AppCloseListener extends Ordered {

    @Override
    default int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 监听程序关闭执行事件
     * @param event 关闭事件
     */
    void onApplicationEvent(ContextClosedEvent event);

}
