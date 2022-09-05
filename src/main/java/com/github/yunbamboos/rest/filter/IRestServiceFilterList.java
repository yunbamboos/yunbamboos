package com.github.yunbamboos.rest.filter;


import java.util.List;

/**
 * 存储{@link IRestServiceFilter} 列表
 */
public interface IRestServiceFilterList {

    /**
     * 通过类型列表获取对应过滤器列表
     * @param filters 过滤器类型
     * @return 返回过滤器列表
     */
    List<IRestServiceFilter> filters(List<Class<? extends IRestServiceFilter>> filters);


}
