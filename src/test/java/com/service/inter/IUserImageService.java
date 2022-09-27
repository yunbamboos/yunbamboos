package com.service.inter;


import com.service.dto.user.QueryUserImageInDTO;
import com.service.dto.user.QueryUserImageOutDTO;

/**
 * 用户相关接口
 */
public interface IUserImageService {

    /**
     * 查询用户头像
     *
     * @param in {link QueryUserImageInDTO}
     * @return {link QueryUserImageOutDTO}
     */
    QueryUserImageOutDTO queryUserImage(QueryUserImageInDTO in);

}
