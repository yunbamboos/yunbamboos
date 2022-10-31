package com.token;

import io.github.yunbamboos.model.Token;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TokenCache {

    private Map<String, Token> cache;

    public TokenCache(int capacity){
        this.cache = new ConcurrentHashMap<>(capacity);
    }

    public Map<String, Token> getCache() {
        return cache;
    }

    public void add(Token token){
        cache.put(token.getTokenId(), token);
    }

    public Optional<Token> get(String tokenId){
        Token token = cache.get(tokenId);
        if(token == null || token.isExpire()){
            return Optional.empty();
        } else {
            return Optional.of(token);
        }
    }

}
