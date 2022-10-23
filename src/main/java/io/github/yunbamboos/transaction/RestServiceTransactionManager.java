package io.github.yunbamboos.transaction;


import io.github.yunbamboos.rest.IRestService;

/**
 * RestService 事务管理
 * */
public interface RestServiceTransactionManager {

    void begin(IRestService restService);

    void commit(IRestService restService);

    void rollback(IRestService restService);

    void release(IRestService restService);
}
