package com.service.impl.user;

import com.mapper.UserImageMapper;
import com.model.UserImage;
import com.service.dto.user.QueryUserImageBase64InDTO;
import com.service.dto.user.QueryUserImageBase64OutDTO;
import com.service.dto.user.QueryUserImageInDTO;
import com.service.dto.user.QueryUserImageOutDTO;
import com.service.inter.IUserImageService;
import io.github.yunbamboos.constant.ContentType;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.anno.Tag;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 用户相关接口实现
 */
@Tag("用户相关接口")
@Service("UserImageServiceImpl")
public class UserImageServiceImpl implements IUserImageService {

    @Resource
    private UserImageMapper userImageMapper;

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/queryUserImageBase64",
            in = QueryUserImageBase64InDTO.class,
            out = QueryUserImageBase64OutDTO.class,
            name = "查询用户头像(base64)接口"
    )
    @Override
    public QueryUserImageBase64OutDTO queryUserImageBase64(QueryUserImageBase64InDTO in) {
        QueryUserImageBase64OutDTO out = new QueryUserImageBase64OutDTO();
        Optional<UserImage> optional =  userImageMapper.queryByUserId(in.getUserId());
        optional.ifPresent(out::setUserImage);
        return out;
    }

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/queryUserImage",
            in = QueryUserImageInDTO.class,
            out = QueryUserImageOutDTO.class,
            name = "查询用户头像接口"
    )
    @Override
    public QueryUserImageOutDTO queryUserImage(QueryUserImageInDTO in) {
        QueryUserImageOutDTO out = new QueryUserImageOutDTO(ContentType.APPLICATION_IMAGE);
        Optional<UserImage> optional =  userImageMapper.queryByUserId(in.getUserId());
        optional.ifPresent(out::setUserImage);
        return out;
    }
}
