package io.github.yunbamboos.application;

import io.github.yunbamboos.config.RestServiceConfig;
import io.github.yunbamboos.config.TransactionManagerConfig;
import io.github.yunbamboos.listener.AppCloseListener;
import io.github.yunbamboos.listener.AppStartListener;
import io.github.yunbamboos.listener.PrintSystemEnvironmentListener;
import io.github.yunbamboos.listener.ScannerRestServerListener;
import io.github.yunbamboos.rest.servlet.RestDispatcherServlet;
import io.github.yunbamboos.util.CollectionUtils;
import io.github.yunbamboos.util.SpringBeanUtils;
import io.github.yunbamboos.util.TokenUtils;
import io.jsonwebtoken.impl.DefaultClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * SpringApplication 启动类封装
 *
 * @see SpringBeanUtils SpringBeanUtils 从spring容器获取bean工具
 * @see RestServiceConfig RestServiceConfig 配置rest服务参数
 */
@Import({
        SpringBeanUtils.class, // 获取bean工具
        RestServiceConfig.class, // 引入rest服务配置
        TransactionManagerConfig.class
})
@ComponentScan(basePackageClasses = {RestDispatcherServlet.class})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final List<AppStartListener> startListenerList;

    private final List<AppCloseListener> closeListenerList;

    private final Class<?>[] primarySources;

    private ConfigurableApplicationContext context;

    /**
     * 创建一个新的{@link Application}实例。将加载应用程序上下文来自指定主源的bean
     *
     * @param primarySources 主要的bean源
     */
    public Application(Class<?>... primarySources) {
        Assert.notNull(primarySources, "PrimarySources must not be null");
        this.primarySources = primarySources;
        this.startListenerList = new ArrayList<>();
        this.closeListenerList = new ArrayList<>();
    }

    /**
     * 启动程序执行
     * @param args 参数
     */
    public void run(String... args) {
        Class<?>[] sources = new Class<?>[primarySources.length + 1];
        System.arraycopy(primarySources, 0, sources, 0, primarySources.length);
        sources[primarySources.length] = Application.class;
        SpringApplication app = new SpringApplication(sources);
        this.addStartListener(event -> TokenUtils.createToken(new DefaultClaims(), System.currentTimeMillis())); // 初始化token 模块
        //扫描 restService 的所有服务
        this.addStartListener(new ScannerRestServerListener());
        this.addStartListener(new PrintSystemEnvironmentListener());
        app.addListeners(new StartListener(startListenerList));
        app.addListeners(new CloseListener(closeListenerList));
        this.context = app.run(args);
        log.info("listener port {}", getServerPort());
    }

    /**
     * 添加启动监听
     *
     * @param appStartListener 启动函数接口
     * @see AppStartListener AppStartListener 监听函数接口
     */
    public void addStartListener(AppStartListener appStartListener) {
        this.startListenerList.add(appStartListener);
    }

    private String getServerPort() {
        if (this.context == null) {
            return "";
        }
        return this.context.getEnvironment().getProperty("server.port", "");
    }

    private record StartListener(List<AppStartListener> listeners) implements ApplicationListener<ContextRefreshedEvent> {

        private static final Logger log = LoggerFactory.getLogger(StartListener.class);

        @Override
        public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
            log.info("start initializing resources");
            if (CollectionUtils.isNotEmpty(listeners)) {
                listeners.sort(Comparator.comparing(AppStartListener::getOrder));
                listeners.forEach(listener -> listener.onApplicationEvent(event));
            }
            log.info("initializing resources complete");
        }
    }

    private record CloseListener(List<AppCloseListener> listeners) implements ApplicationListener<ContextClosedEvent> {

        private static final Logger log = LoggerFactory.getLogger(CloseListener.class);

        @Override
        public void onApplicationEvent(@NonNull ContextClosedEvent event) {
            log.info("start releasing resources");
            if (CollectionUtils.isNotEmpty(listeners)) {
                listeners.sort(Comparator.comparing(AppCloseListener::getOrder));
                listeners.forEach(listener -> listener.onApplicationEvent(event));
            }
            log.info("release resource complete");
        }

    }

}
