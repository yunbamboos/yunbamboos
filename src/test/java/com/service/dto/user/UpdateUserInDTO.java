package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import com.model.User;
import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

public class UpdateUserInDTO extends InDTO {

    @ParamDesc(name = "user", zn = "用户信息", isNull = true, type = ParamType.Object)
    private User user;

    @Override
    public void decode(JSONObject json) {
        this.user = this.getRequiredObject(json, "user", User.class);
    }

    public User getUser() {
        return user;
    }

}
