package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import com.model.User;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;

public class QueryCurLoginUserOutDTO extends OutDTO {

    @ParamDesc(name = "user", zn = "用户信息", isNull = true, type = ParamType.Object)
    private User user;

    @Override
    public JSONObject encodeData(){
        JSONObject json = new JSONObject();
        if (ObjectUtils.isNotNull(user)) {
            json.put("user", user.encode());
        }
        return json;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
