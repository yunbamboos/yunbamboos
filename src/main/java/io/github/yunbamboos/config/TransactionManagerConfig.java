package io.github.yunbamboos.config;

import io.github.yunbamboos.transaction.DefaultRestServiceTransactionManager;
import io.github.yunbamboos.transaction.DefaultTransactionRestServiceConfig;
import io.github.yunbamboos.transaction.RestServiceTransactionManager;
import io.github.yunbamboos.transaction.TransactionRestServiceConfig;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

@Aspect
@Configuration
@Order(0)
public class TransactionManagerConfig {

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Resource
    private DefaultTransactionDefinition transactionDefinition;

    @Bean
    public TransactionRestServiceConfig createTransactionRestServiceConfig() {
        return new DefaultTransactionRestServiceConfig(true);
    }

    @Bean
    public RestServiceTransactionManager createRestServiceTransactionManager(TransactionRestServiceConfig transactionRestServiceConfig) {
        return new DefaultRestServiceTransactionManager(dataSourceTransactionManager,
                transactionDefinition,
                transactionRestServiceConfig);
    }
}
