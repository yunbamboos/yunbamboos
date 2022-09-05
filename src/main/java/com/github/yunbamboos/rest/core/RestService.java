package com.github.yunbamboos.rest.core;

import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.rest.IRestService;
import com.github.yunbamboos.rest.filter.IRestServiceFilter;
import com.github.yunbamboos.rest.proxy.CreateInvokeService;
import com.github.yunbamboos.rest.proxy.InvokeService;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public class RestService implements IRestService {

    RequestMethod requestMethod;
    String url;
    Class<? extends InDTO> in;
    Class<? extends OutDTO> out;
    String name;
    boolean authentication;
    List<Class<? extends IRestServiceFilter>> filters;
    Object bean;
    Class<?> interfaceClass;
    Class<?> serviceClass;
    String methodName;
    InvokeService invokeService;

    @Override
    public RequestMethod getRequestMethod() {
        return this.requestMethod;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Class<? extends InDTO> getIn() {
        return this.in;
    }

    @Override
    public Class<? extends OutDTO> getOut() {
        return this.out;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isAuthentication() {
        return this.authentication;
    }

    @Override
    public List<Class<? extends IRestServiceFilter>> getFilters() {
        return this.filters;
    }

    @Override
    public Object getBean(){
        return bean;
    }

    @Override
    public void createInvokeService() {
        this.invokeService = new CreateInvokeService(interfaceClass, serviceClass, methodName, in, out).create();
    }

    @Override
    public Optional<InvokeService> getInvoke() {
        return Optional.ofNullable(invokeService);
    }

}
