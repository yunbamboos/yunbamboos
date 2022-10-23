package io.github.yunbamboos.transaction;

import io.github.yunbamboos.rest.IRestService;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultTransactionRestServiceConfig implements TransactionRestServiceConfig {

    private final boolean enable;

    private final Map<String, Transaction> transactionMap;

    public DefaultTransactionRestServiceConfig(boolean enable) {
        this.enable = enable;
        this.transactionMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Transaction> getTransaction(IRestService restService) {
        return enable ? Optional.ofNullable(transactionMap.get(restService.getUrl())) : Optional.empty();
    }

    public void add(IRestService restService) {
        transactionMap.put(restService.getUrl(), restService.transaction());
    }
}
