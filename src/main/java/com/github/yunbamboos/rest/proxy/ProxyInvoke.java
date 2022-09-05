package com.github.yunbamboos.rest.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvoke implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(ProxyInvoke.class);

    private final Object target;//真实的对象

    public ProxyInvoke(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(log.isTraceEnabled()){
            log.trace("方法前后打印日志:--------before");
        }
        Object invoke = method.invoke(target, args); //执行对象的方法
        if(log.isTraceEnabled()){
            log.trace("方法前后打印日志--------after");
        }
        return invoke;
    }

}
