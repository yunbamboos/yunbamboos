package com.service.dto.login;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.dto.in.InDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

/**
 * 登录接口入参
 */
public class LoginInDTO extends InDTO {

    @ParamDesc(name = "login_name", zn = "登录名", isNull = true, type = ParamType.String, demo = "yunzhu")
    private String loginName;
    @ParamDesc(name = "password", zn = "登陆密码", isNull = true, type = ParamType.String, demo = "123")
    private String password;

    @Override
    public void decode(JSONObject json) {
        this.loginName = this.getRequiredString(json, "login_name");
        this.password = this.getRequiredString(json, "password");
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }
}
