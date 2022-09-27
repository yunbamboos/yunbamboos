package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.dto.in.PageInDTO;

public class QueryUserByPageInDTO extends PageInDTO {

    @Override
    public void decode(JSONObject json) {
        super.decode(json);
    }

}
