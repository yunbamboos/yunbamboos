package io.github.yunbamboos.transaction;


/**
 * RestService 事务管理
 * */
public interface RestServiceTransactionManager {

    void begin();

    void commit();

    void rollback();

    void release();
}
