package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import com.model.User;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;

public class AddUserOutDTO extends OutDTO {

    @ParamDesc(name = "user_id", zn = "用户ID", isNull = true, type = ParamType.Integer)
    private int userId;

    @Override
    public JSONObject encodeData() {
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        return json;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
