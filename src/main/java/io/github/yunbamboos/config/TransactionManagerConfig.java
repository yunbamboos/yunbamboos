package io.github.yunbamboos.config;

import io.github.yunbamboos.transaction.JdbcRestServiceTransactionManager;
import io.github.yunbamboos.transaction.RestServiceTransactionManager;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.annotation.Resource;

@Aspect
@Configuration
@Order(0)
public class TransactionManagerConfig {

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    @Bean
    public RestServiceTransactionManager createRestServiceTransactionManager(){
        // TODO 读取事务配置
        return new JdbcRestServiceTransactionManager(dataSourceTransactionManager, transactionDefinition);
    }
}
