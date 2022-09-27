package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

public class QueryUserImageInDTO extends InDTO {

    @ParamDesc(name = "user_id", zn = "用户ID", isNull = true, type = ParamType.Object)
    private int userId;

    @Override
    public void decode(JSONObject json) {
        this.userId = this.getIntValue(json, "user_id", 0);
    }

    public int getUserId() {
        return userId;
    }
}
