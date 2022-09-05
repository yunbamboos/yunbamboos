package io.github.yunbamboos.rest.filter;



import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.IRestService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultRestServiceFilterExchange implements RestServiceFilterExchange{

    private IRestService restService;
    private InDTO in;
    private OutDTO out;
    private final Map<String,Object> attributes;

    public DefaultRestServiceFilterExchange(){
        this.attributes = new ConcurrentHashMap<>(20);
    }

    @Override
    public IRestService restService(){
        return restService;
    }

    @Override
    public void restService(IRestService restService){
        this.restService = restService;
    }

    @Override
    public InDTO in() {
        return in;
    }

    @Override
    public void in(InDTO in) {
        this.in = in;
    }

    @Override
    public OutDTO out() {
        return out;
    }

    @Override
    public void out(OutDTO out) {
        this.out = out;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
