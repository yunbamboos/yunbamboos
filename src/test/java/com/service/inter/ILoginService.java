package com.service.inter;


import com.service.dto.login.LoginInDTO;
import com.service.dto.login.LoginOutDTO;

/**
 * 登录相关接口
 * */
public interface ILoginService {

    /**
     * 用户登录接口
     * @param in {@link LoginInDTO 登录接口入参}
     * @return {@link LoginOutDTO 登录接口出参}
     * */
    LoginOutDTO login(LoginInDTO in);
}
