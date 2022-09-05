package io.github.yunbamboos.rest.core;

import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.rest.filter.IRestServiceFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class RestServiceBuilder implements IRestService.Builder {

    private RequestMethod requestMethod;
    private String url;
    private Class<? extends InDTO> in;
    private Class<? extends OutDTO> out;
    private String name;
    private boolean authentication;
    private List<Class<? extends IRestServiceFilter>> filters;
    private Object bean;
    private Class<?> interfaceClass;
    private Class<?> serviceClass;
    private String methodName;

    @Override
    public IRestService.Builder requestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    @Override
    public IRestService.Builder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public IRestService.Builder in(Class<? extends InDTO> in) {
        this.in = in;
        return this;
    }

    @Override
    public IRestService.Builder out(Class<? extends OutDTO> out) {
        this.out = out;
        return this;
    }

    @Override
    public IRestService.Builder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IRestService.Builder authentication(boolean authentication) {
        this.authentication = authentication;
        return this;
    }

    @Override
    public IRestService.Builder filters(List<Class<? extends IRestServiceFilter>> filters) {
        this.filters = filters;
        return this;
    }

    @Override
    public IRestService.Builder bean(Object bean) {
        this.bean = bean;
        return this;
    }

    @Override
    public IRestService.Builder interfaceClass(Class<?> interfaceClass){
        this.interfaceClass = interfaceClass;
        return this;
    }

    @Override
    public IRestService.Builder serviceClass(Class<?> serviceClass){
        this.serviceClass = serviceClass;
        return this;
    }

    @Override
    public IRestService.Builder methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    @Override
    public IRestService build() {
        RestService restService = new RestService();
        restService.requestMethod = requestMethod;
        restService.url = url;
        restService.in = in;
        restService.out = out;
        restService.name = name;
        restService.authentication = authentication;
        restService.filters = filters;
        restService.bean = bean;
        restService.interfaceClass = interfaceClass;
        restService.serviceClass = serviceClass;
        restService.methodName = methodName;
        restService.createInvokeService();
        return restService;
    }
}
