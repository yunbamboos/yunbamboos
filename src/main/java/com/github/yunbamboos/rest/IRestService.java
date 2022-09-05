package com.github.yunbamboos.rest;

import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.rest.filter.IRestServiceFilter;
import com.github.yunbamboos.rest.proxy.InvokeService;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

/**
 * rest service 服务接口
 */
public interface IRestService {

    RequestMethod getRequestMethod();

    String getUrl();

    Class<? extends InDTO> getIn();

    Class<? extends OutDTO> getOut();

    String getName();

    boolean isAuthentication();

    List<Class<? extends IRestServiceFilter>> getFilters();

    Object getBean();

    void createInvokeService();

    Optional<InvokeService> getInvoke();

    interface Builder {

        Builder requestMethod(RequestMethod requestMethod);

        Builder url(String url);

        Builder in(Class<? extends InDTO> in);

        Builder out(Class<? extends OutDTO> out);

        Builder name(String name);

        Builder authentication(boolean authentication);

        Builder filters(List<Class<? extends IRestServiceFilter>> filters);

        Builder bean(Object bean);

        Builder interfaceClass(Class<?> interfaceClass);

        Builder serviceClass(Class<?> serviceClass);

        Builder methodName(String methodName);

        IRestService build();
    }
}
