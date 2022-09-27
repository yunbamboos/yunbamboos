package com.service.dto.login;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.model.Token;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;
/**
 * 登录接口出参
 * */
public class LoginOutDTO extends OutDTO {

    @ParamDesc(name = "token", zn = "token", isNull = true, type = ParamType.Object)
    private Token token;

    @Override
    public JSONObject encodeData(){
        JSONObject json = new JSONObject();
        if (ObjectUtils.isNotNull(token)) {
            json.put("token", token.encode());
        }
        return json;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
