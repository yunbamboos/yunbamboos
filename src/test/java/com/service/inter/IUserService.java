package com.service.inter;


import com.service.dto.user.AddUserInDTO;
import com.service.dto.user.AddUserOutDTO;
import com.service.dto.user.DelUserInDTO;
import com.service.dto.user.DelUserOutDTO;
import com.service.dto.user.QueryCurLoginUserInDTO;
import com.service.dto.user.QueryCurLoginUserOutDTO;
import com.service.dto.user.QueryUserByPageInDTO;
import com.service.dto.user.QueryUserByPageOutDTO;
import com.service.dto.user.UpdateUserInDTO;
import com.service.dto.user.UpdateUserOutDTO;

/**
 * 用户相关接口
 * */
public interface IUserService {

    /**查询当前登录用户*/
    QueryCurLoginUserOutDTO queryCurLoginUser(QueryCurLoginUserInDTO in);
    /**条件分页查询用户列表*/
    QueryUserByPageOutDTO queryByPage(QueryUserByPageInDTO in);
    /**添加新用户*/
    AddUserOutDTO add(AddUserInDTO in);
    /**更新用户*/
    UpdateUserOutDTO update(UpdateUserInDTO in);
    /**删除用户*/
    DelUserOutDTO del(DelUserInDTO in);
}
