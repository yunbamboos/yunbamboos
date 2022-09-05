package io.github.yunbamboos.rest.scanner;

import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.rest.IRestServiceList;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.core.RestServiceBuilder;
import io.github.yunbamboos.rest.filter.IRestServiceFilter;
import io.github.yunbamboos.rest.filter.InvokeBeanRestServiceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RestService 服务扫描仪
 * <p> 方法带 {@see RestServiceType} 注解
 */
public class RestServiceScanner {

    private static final Logger log = LoggerFactory.getLogger(RestServiceScanner.class);

    private static final Pattern CLASS_PATTERN = Pattern.compile("(com.*)");

    private static final String SEPARATOR = "/";

    private static final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private final ApplicationContext applicationContext;
    /**
     * 服务列表
     */
    private final IRestServiceList restServiceList;
    /**
     * 扫描包路径
     */
    private final String[] pages;
    /**
     * 扫描接口列表
     */
    private final Set<Class<?>> interfaces;

    public RestServiceScanner(ApplicationContext applicationContext, IRestServiceList restServiceList, String[] pages) {
        this.applicationContext = applicationContext;
        this.restServiceList = restServiceList;
        this.pages = pages;
        this.interfaces = new LinkedHashSet<>(300);
    }

    public void scan() {
        this.scanInterfaceClass();
        this.scanBeanByInterface();
    }

    /**
     * 扫描所有的服务接口类
     */
    private void scanInterfaceClass() {
        for (String basePackage : pages) {
            String basePath = basePackage.replace(".", SEPARATOR);
            String packagePath = "classpath*:" + basePath + SEPARATOR + "**" + SEPARATOR + "*.class";
            if (log.isDebugEnabled()) {
                log.debug("scan package path:{}", packagePath);
            }
            try {
                readResource(packagePath);
            } catch (Exception e) {
                log.error("scan page : {} error! error message : {}", basePackage, e.getMessage());
            }
        }
    }

    private void readResource(String packagePath) throws IOException {
        Resource[] rs = resourcePatternResolver.getResources(packagePath);
        for (int len = rs.length, j = 0; j < len; j++) {
            Resource r = rs[j];
            String filePath = r.getURL().toString();
            if (r instanceof UrlResource) {
                filePath = filePath.substring(filePath.indexOf(".jar!") + 6);
            }
            if (log.isDebugEnabled()) {
                log.debug("filePath={}", filePath);
            }
            Matcher m = CLASS_PATTERN.matcher(filePath);
            if (m.find()) {
                String cn = m.group();
                cn = cn.replace("/", ".");
                cn = cn.replace(".class", "");
                interfaces.add(forName(cn));
            }
        }
    }

    private Class<?> forName(String className) {
        try {
            ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
            return ClassUtils.forName(className, defaultClassLoader);
        } catch (ClassNotFoundException | LinkageError e) {
            e.printStackTrace();
        }
        return null;
    }

    private void scanBeanByInterface() {
        for (Class<?> cls : this.interfaces) {
            String[] beanNames = applicationContext.getBeanNamesForType(cls);
            for (String beanName : beanNames) {
                Object bean = applicationContext.getBean(beanName);
                Class<?> beanClass = bean.getClass();
                for (Method method : beanClass.getMethods()) {
                    this.readRestServerFromMethod(cls, beanClass, bean, method);
                }
            }
        }
    }

    private void readRestServerFromMethod(Class<?> interfaceClass, Class<?> serviceClass, Object bean, Method method) {
        RestServiceType restServiceType = method.getAnnotation(RestServiceType.class);
        if (restServiceType != null) {
            IRestService restService = new RestServiceBuilder()
                    .requestMethod(restServiceType.method())
                    .url(restServiceType.url())
                    .in(restServiceType.in())
                    .out(restServiceType.out())
                    .name(restServiceType.name())
                    .authentication(restServiceType.authentication())
                    .filters(arrayToList(restServiceType.filters()))
                    .bean(bean)
                    .interfaceClass(interfaceClass)
                    .serviceClass(serviceClass)
                    .methodName(method.getName())
                    .build();
            restServiceList.add(restService);
        }
    }

    private List<Class<? extends IRestServiceFilter>> arrayToList(Class<? extends IRestServiceFilter>[] filters) {
        List<Class<? extends IRestServiceFilter>> list = new ArrayList<>(filters.length + 1);
        Collections.addAll(list, filters);
        list.add(InvokeBeanRestServiceFilter.class);
        return list;
    }

}
