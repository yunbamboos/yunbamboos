package com.service.impl.login;

import com.mapper.UserMapper;
import com.model.User;
import com.service.dto.login.LoginInDTO;
import com.service.dto.login.LoginOutDTO;
import com.service.inter.ILoginService;
import io.github.yunbamboos.exception.AppException;
import io.github.yunbamboos.model.Token;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.anno.Tag;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 登录相关接口实现
 */
@Tag("登录相关接口")
@Service("LoginServiceImpl")
public class LoginServiceImpl implements ILoginService {

    @Resource
    private UserMapper userMapper;

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/login/login",
            in = LoginInDTO.class,
            out = LoginOutDTO.class,
            name = "用户登录接口",
            authentication = false
    )
    @Override
    public LoginOutDTO login(LoginInDTO in) {
        LoginOutDTO out = new LoginOutDTO();
        User user = userMapper.queryByLoginNameAndPassword(in.getLoginName(), in.getPassword()).orElseThrow(() -> {
            throw new AppException("用户不存在,或者账号密码错误");
        });
        if (user.getUserState() != 1) {
            throw new AppException("当前用户状态不正常，请联系管理员");
        } else {
            Token token = new Token();
            token.set("user_id", user.getUserId());
            token.set("role_id", user.getRoleId());
            token.createToken();
            out.setToken(token);
        }
        return out;
    }
}
