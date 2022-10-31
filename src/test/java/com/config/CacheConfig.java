package com.config;

import com.token.TokenCache;
import com.token.TokenCacheClearThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * token 缓存配置
 */
@Configuration
@Order(2)
public class CacheConfig {

    @Bean
    public TokenCache createTokenCache() {
        return new TokenCache(300);
    }

    @Bean
    public ScheduledExecutorService createScheduledExecutorService(TokenCache tokenCache) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);
        service.scheduleAtFixedRate(
                new TokenCacheClearThread(tokenCache),
                10000L,
                60*60*1000L, TimeUnit.MILLISECONDS);
        return service;
    }
}
