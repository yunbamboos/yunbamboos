package io.github.yunbamboos.rest.filter;

import io.github.yunbamboos.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestServiceFilterList implements IRestServiceFilterList {

    private final Map<Class<? extends IRestServiceFilter>, IRestServiceFilter> cache;

    public RestServiceFilterList(List<IRestServiceFilter> filters) {
        this.cache = new ConcurrentHashMap<>(filters.size());
        if (CollectionUtils.isNotEmpty(filters)) {
            filters.forEach(filter -> cache.put(filter.getClass(), filter));
        }
    }

    @Override
    public List<IRestServiceFilter> filters(List<Class<? extends IRestServiceFilter>> filters) {
        List<IRestServiceFilter> filterList = new ArrayList<>(filters.size());
        for (Class<? extends IRestServiceFilter> filter : filters) {
            if (cache.containsKey(filter)) {
                filterList.add(cache.get(filter));
            }
        }
        Collections.sort(filterList);
        return filterList;
    }
}
