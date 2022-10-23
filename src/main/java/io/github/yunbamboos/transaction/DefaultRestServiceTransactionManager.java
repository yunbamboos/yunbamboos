package io.github.yunbamboos.transaction;

import io.github.yunbamboos.rest.IRestService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public record DefaultRestServiceTransactionManager(
        DataSourceTransactionManager dataSourceTransactionManager,
        DefaultTransactionDefinition transactionDefinition,
        TransactionRestServiceConfig transactionRestServiceConfig) implements RestServiceTransactionManager {

    private static final ThreadLocal<RestServiceTransactionStatus> local = new ThreadLocal<>();

    @Override
    public void begin(IRestService restService) {
        Transaction transaction = transactionRestServiceConfig.getTransaction(restService).orElse(Transaction.empty);
        if(transaction.equals(Transaction.read_only)){
            transactionDefinition.setReadOnly(true);// 设置只读事务
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            local.set(new RestServiceTransactionStatus(transactionStatus).begin());
        }
        if(transaction.equals(Transaction.modify)){
            transactionDefinition.setReadOnly(false);
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            local.set(new RestServiceTransactionStatus(transactionStatus).begin());
        }
    }

    @Override
    public void commit(IRestService restService) {
        RestServiceTransactionStatus status = local.get();
        if (status != null) {
            dataSourceTransactionManager.commit(status.getTransactionStatus());
            status.commit();
        }
    }

    @Override
    public void rollback(IRestService restService) {
        RestServiceTransactionStatus status = local.get();
        if (status != null) {
            dataSourceTransactionManager.rollback(status.getTransactionStatus());
            status.rollback();
        }
    }

    @Override
    public void release(IRestService restService) {
        local.remove();
    }

}
