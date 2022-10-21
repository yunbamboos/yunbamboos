package io.github.yunbamboos.transaction;

import org.springframework.transaction.TransactionStatus;

public class RestServiceTransactionStatus {
    /**
     * 1 开始状态
     * 2 提交状态
     * 3 回滚状态
     */
    private int status;

    private final TransactionStatus transactionStatus;

    public RestServiceTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public RestServiceTransactionStatus begin() {
        this.status = 1;
        return this;
    }

    public RestServiceTransactionStatus commit() {
        this.status = 2;
        return this;
    }

    public RestServiceTransactionStatus rollback() {
        this.status = 3;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
}
