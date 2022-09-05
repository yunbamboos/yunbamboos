package com.github.yunbamboos.listener;

import com.github.yunbamboos.rest.IRestServiceList;
import com.github.yunbamboos.rest.anno.RestServiceScan;
import com.github.yunbamboos.rest.scanner.RestServiceScanner;
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
        log.info("rest service path {}", list);
        String[] pages = new String[list.size()];

        list.toArray(pages);
        new RestServiceScanner(applicationContext, restServiceList, pages).scan();
    }
}
