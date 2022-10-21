package io.github.yunbamboos.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * 输出系统参数
 * */
public class PrintSystemEnvironmentListener implements AppStartListener {

    private static final Logger log = LoggerFactory.getLogger(PrintSystemEnvironmentListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("http listener port {}", event.getApplicationContext().getEnvironment().getProperty("server.port",""));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}