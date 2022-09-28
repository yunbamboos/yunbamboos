package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

public class DelUserInDTO extends InDTO {

    @ParamDesc(name = "user_id", zn = "用户ID", isNull = true, type = ParamType.Integer)
    private int userId;

    @Override
    public void decode(JSONObject json) {
        this.userId = this.getRequiredInteger(json, "user_id");
    }

    public int getUserId() {
        return userId;
    }
}
