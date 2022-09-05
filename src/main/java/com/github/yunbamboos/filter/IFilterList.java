package com.github.yunbamboos.filter;

import java.util.List;

/**
 * 储存过滤器列表
 */
public interface IFilterList {

    /**获取过滤器列表*/
    List<IFilter> getFilters();
}
