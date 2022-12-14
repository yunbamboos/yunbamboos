package com.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public record TokenCacheClearThread(TokenCache tokenCache) implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(TokenCacheClearThread.class);

    @Override
    public void run() {
        AtomicInteger count = new AtomicInteger();
        tokenCache.getCache().entrySet().removeIf(entry -> {
            boolean expire = entry.getValue().isExpire();
            if(expire){
                count.getAndIncrement();
            }
            return expire;
        });
        if (count.get() > 0) {
            log.info("clear expire token {}", count.get());
        }
    }

}
