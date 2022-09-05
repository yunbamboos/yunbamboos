package com.github.yunbamboos.rest;

import java.util.Optional;

public interface IRestServiceList {

    void add(IRestService restService);

    Optional<IRestService> get(String url);

}
