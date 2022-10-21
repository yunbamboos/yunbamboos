package io.github.yunbamboos.listener;

import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 启动完调用 读取事务全局配置
 */
public record ReadTransactionAnnotationListener(Class<?>[] sources) implements AppStartListener {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (Class<?> source : sources) {

        }
    }

}
