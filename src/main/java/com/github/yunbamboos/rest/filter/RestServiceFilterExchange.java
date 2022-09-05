package com.github.yunbamboos.rest.filter;

import com.github.yunbamboos.dto.in.InDTO;
import com.github.yunbamboos.dto.out.OutDTO;
import com.github.yunbamboos.rest.IRestService;
import org.springframework.lang.Nullable;

import java.util.Map;

public interface RestServiceFilterExchange {

    IRestService restService();

    void restService(IRestService restService);

    InDTO in();

    void in(InDTO in);

    OutDTO out();

    void out(OutDTO out);

    Map<String, Object> getAttributes();

    @SuppressWarnings("unchecked")
    @Nullable
    default <T> T getAttribute(String name) {
        return (T) getAttributes().get(name);
    }
}
