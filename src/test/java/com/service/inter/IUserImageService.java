package com.service.inter;


import com.service.dto.user.QueryUserImageBase64InDTO;
import com.service.dto.user.QueryUserImageBase64OutDTO;
import com.service.dto.user.QueryUserImageInDTO;
import com.service.dto.user.QueryUserImageOutDTO;

/**
 * 用户相关接口
 */
public interface IUserImageService {

    /**
     * 查询用户头像接口(base64)
     *
     * @param in {link QueryUserImageBase64InDTO}
     * @return {link QueryUserImageBase64OutDTO} base64
     */
    QueryUserImageBase64OutDTO queryUserImageBase64(QueryUserImageBase64InDTO in);

    /**
     * 查询用户头像
     *
     * @param in {link QueryUserImageInDTO}
     * @return {link QueryUserImageOutDTO} base64
     */
    QueryUserImageOutDTO queryUserImage(QueryUserImageInDTO in);
}
