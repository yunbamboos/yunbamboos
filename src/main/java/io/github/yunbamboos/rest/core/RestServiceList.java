package io.github.yunbamboos.rest.core;

import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.rest.IRestService;
import io.github.yunbamboos.rest.IRestServiceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RestServiceList implements IRestServiceList {

    private static final Logger log = LoggerFactory.getLogger(RestServiceList.class);

    private final Map<String, IRestService> restServiceMap;

    public RestServiceList() {
        this.restServiceMap = new ConcurrentHashMap<>(200);
    }

    @Override
    public void add(IRestService restService) {
        log.info("add rest service {}({})", restService.getName(), restService.getUrl());
        String url = restService.getUrl();
        if (this.restServiceMap.containsKey(url)) {
            throw new AppException("rest service[" + url + "] already exists");
        } else {
            this.restServiceMap.put(restService.getUrl(), restService);
        }
    }

    @Override
    public Optional<IRestService> get(String url) {
        return Optional.ofNullable(restServiceMap.get(url));
    }

}
