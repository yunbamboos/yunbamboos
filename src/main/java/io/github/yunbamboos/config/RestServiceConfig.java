package io.github.yunbamboos.config;

import io.github.yunbamboos.filter.AuthenticationFilter;
import io.github.yunbamboos.filter.FilterList;
import io.github.yunbamboos.filter.HttpRequestMethodFilter;
import io.github.yunbamboos.filter.IFilter;
import io.github.yunbamboos.filter.IFilterList;
import io.github.yunbamboos.filter.InvokeRestServiceFilter;
import io.github.yunbamboos.filter.LoadServiceFilter;
import io.github.yunbamboos.filter.OutDTOToResponseFilter;
import io.github.yunbamboos.filter.ReadRequestFilter;
import io.github.yunbamboos.filter.RequestToInDTOFilter;
import io.github.yunbamboos.filter.WriteResponseFilter;
import io.github.yunbamboos.listener.AuthenticationListener;
import io.github.yunbamboos.rest.ICallService;
import io.github.yunbamboos.rest.IRestServiceList;
import io.github.yunbamboos.rest.core.CallService;
import io.github.yunbamboos.rest.core.RestServiceList;
import io.github.yunbamboos.rest.filter.DefaultRestServiceFilterHandler;
import io.github.yunbamboos.rest.filter.IRestServiceFilter;
import io.github.yunbamboos.rest.filter.IRestServiceFilterHandler;
import io.github.yunbamboos.rest.filter.IRestServiceFilterList;
import io.github.yunbamboos.rest.filter.InvokeBeanRestServiceFilter;
import io.github.yunbamboos.rest.filter.RestServiceFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;


/**
 * RestService配置
 */
@Configuration
public class RestServiceConfig {

    private static final Logger log = LoggerFactory.getLogger(RestServiceConfig.class);

    /**
     * <p>创建过滤器列表类
     * <p>管理注册的{@see IFilter}过滤器
     *
     * @return 返回{@link IFilterList}类型实例
     */
    @Bean
    @ConditionalOnMissingBean({IFilterList.class})
    public IFilterList createFilterList(List<IFilter> filters) {
        Collections.sort(filters);
        if (log.isInfoEnabled()) {
            int i = 1;
            for (IFilter filter : filters) {
                log.info("filters({}): {}", i++, filter);
            }
        }
        return new FilterList(filters);
    }

    /**
     * <p>创建提供服务列表类
     * <p>管理所有
     * <p>接口 com.service.inter
     * <p>接口实现 com.service.impl
     *
     * @return 返回{@link IRestServiceList}类型实例
     */
    @Bean
    @ConditionalOnMissingBean({IRestServiceList.class})
    public IRestServiceList createRestServiceList() {
        return new RestServiceList();
    }

    /**
     * 创建一个{@link ICallService}类型实例添加到bean容器
     *
     * @param filterList      bean容器{@link IFilterList}
     * @param restServiceList bean容器{@link IRestServiceList}
     * @return 返回{@link ICallService}类型实例
     */
    @Bean
    @ConditionalOnBean({IFilterList.class, IRestServiceList.class})
    public ICallService createCallService(IFilterList filterList, IRestServiceList restServiceList) {
        return new CallService(filterList, restServiceList);
    }

    /**
     * 创建一个请求方法过滤器实例
     *
     * @return 返回请求方法过滤器实例{@link HttpRequestMethodFilter}
     */
    @Bean
    public IFilter createHttpRequestMethodFilter() {
        return new HttpRequestMethodFilter();
    }

    /**
     * 创建一个加载服务过滤器实例
     *
     * @return 返回加载服务过滤器实例{@link LoadServiceFilter}
     */
    @Bean
    public IFilter createLoadServiceFilter(IRestServiceList restServiceList) {
        return new LoadServiceFilter(restServiceList);
    }

    /**
     * 创建一个权限认证过滤器
     *
     * @return 返回一个权限认证过滤器{@link LoadServiceFilter}
     */
    @Bean
    public IFilter createAuthenticationFilter(List<AuthenticationListener> authenticationListenerList) {
        return new AuthenticationFilter(authenticationListenerList);
    }

    /**
     * 创建一个读取request内容过滤器实例
     *
     * @return 返回读取request内容过滤器实例{@link ReadRequestFilter}
     */
    @Bean
    public IFilter createReadRequestFilter() {
        return new ReadRequestFilter();
    }

    /**
     * 创建一个request转换InDTO过滤器实例
     *
     * @return 返回request转换InDTO过滤器实例{@link RequestToInDTOFilter}
     */
    @Bean
    public IFilter createRequestToInDTOFilter() {
        return new RequestToInDTOFilter();
    }

    /**
     * 创建一个调用RestService过滤器实例
     *
     * @return 返回调用RestService过滤器实例{@link InvokeRestServiceFilter}
     */
    @Bean
    public IFilter createInvokeRestServiceFilter(IRestServiceFilterHandler restServiceFilterHandler, IRestServiceFilterList restServiceFilterList) {
        return new InvokeRestServiceFilter(restServiceFilterHandler, restServiceFilterList);
    }

    /**
     * 创建一个OutDTO 转化 Response过滤器实例
     *
     * @return 返回个OutDTO 转化 Response过滤器实例{@link OutDTOToResponseFilter}
     */
    @Bean
    public IFilter createOutDTOToResponseFilter() {
        return new OutDTOToResponseFilter();
    }

    /**
     * 创建一个写response过滤器实例
     *
     * @return 返回写response过滤器实例{@link WriteResponseFilter}
     */
    @Bean
    public IFilter createWriteResponseFilter() {
        return new WriteResponseFilter();
    }

    /**
     * <p>创建RestServiceFilter
     * <p>管理注册的{@see IRestServiceFilter}过滤器
     *
     * @return 返回{@link IRestServiceFilterList}类型实例
     */
    @Bean
    @ConditionalOnMissingBean({IRestServiceFilterList.class})
    public IRestServiceFilterList createRestServiceFilterList(List<IRestServiceFilter> filters) {
        Collections.sort(filters);
        if (log.isInfoEnabled()) {
            log.info("rest service filters({}): {}", filters.size(), filters);
        }
        return new RestServiceFilterList(filters);
    }

    /**
     * 创建一个IRestServiceFilter类型实例
     *
     * @return 返回创建一个IRestServiceFilter类型实例
     */
    @Bean
    public IRestServiceFilter createInvokeBeanRestServiceFilter() {
        return new InvokeBeanRestServiceFilter();
    }

    @Bean
    public IRestServiceFilterHandler createRestServiceFilterHandler() {
        return new DefaultRestServiceFilterHandler();
    }
}
