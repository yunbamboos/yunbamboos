package io.github.yunbamboos.transaction;

import io.github.yunbamboos.rest.IRestService;

import java.util.Optional;

public interface TransactionRestServiceConfig {

    default boolean isEnable() {
        return false;
    }

    Optional<Transaction> getTransaction(IRestService restService);

    void add(IRestService restService);
}
