package io.github.yunbamboos.listener;

import io.github.yunbamboos.rest.IRestServiceList;
import io.github.yunbamboos.rest.anno.RestServiceScan;
import io.github.yunbamboos.rest.scanner.RestServiceScanner;
import io.github.yunbamboos.transaction.TransactionRestServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 启动完调用 扫描所有服务
 */
public class ScannerRestServerListener implements AppStartListener {

    private static final Logger log = LoggerFactory.getLogger(ScannerRestServerListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        String[] beans = applicationContext.getBeanNamesForAnnotation(RestServiceScan.class);
        List<String> list = new ArrayList<>();
        for(String bean:beans){
            Class<?> beanClass = applicationContext.getBean(bean).getClass();
            RestServiceScan serviceScan = beanClass.getAnnotation(RestServiceScan.class);
            String[] values = serviceScan.value();
            list.addAll(Arrays.asList(values));
        }
        IRestServiceList restServiceList = applicationContext.getBean(IRestServiceList.class);
        TransactionRestServiceConfig transactionRestServiceConfig = applicationContext.getBean(TransactionRestServiceConfig.class);
        log.info("rest service path {}", list);
        String[] pages = new String[list.size()];
        list.toArray(pages);
        new RestServiceScanner(applicationContext, restServiceList, transactionRestServiceConfig, pages).scan();
    }
}
