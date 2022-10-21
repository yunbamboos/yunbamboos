package io.github.yunbamboos.transaction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

public record JdbcRestServiceTransactionManager(
        DataSourceTransactionManager dataSourceTransactionManager,
        TransactionDefinition transactionDefinition) implements RestServiceTransactionManager {

    private static final Logger log = LoggerFactory.getLogger(RestServiceTransactionManager.class);

    private static final ThreadLocal<RestServiceTransactionStatus> local = new ThreadLocal<>();

    @Override
    public void begin() {
        log.info("开启事务");
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        local.set(new RestServiceTransactionStatus(transactionStatus).begin());
    }

    @Override
    public void commit() {
        RestServiceTransactionStatus status = local.get();
        if (status != null) {
            log.info("提交事务");
            dataSourceTransactionManager.commit(status.getTransactionStatus());
        }
    }

    @Override
    public void rollback() {
        RestServiceTransactionStatus status = local.get();
        if (status != null) {
            log.info("回滚事务");
            dataSourceTransactionManager.rollback(status.getTransactionStatus());
        }
    }

    @Override
    public void release(){
        local.remove();
    }
}
