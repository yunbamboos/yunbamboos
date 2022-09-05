package io.github.yunbamboos.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取spring容器bean工具
 */
@Component
public class SpringBeanUtils {

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static <T> List<T> getBeanNamesForType(Class<T> clazz) {
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);
        List<T> list = new ArrayList<>();
        for (String beanName : beanNames) {
            list.add(getBean(beanName, clazz));
        }
        return list;
    }
}
