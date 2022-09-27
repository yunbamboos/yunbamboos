package com.service.impl.user;

import com.mapper.UserImageMapper;
import com.model.UserImage;
import com.service.dto.user.QueryCurLoginUserInDTO;
import com.service.dto.user.QueryCurLoginUserOutDTO;
import com.service.dto.user.QueryUserImageInDTO;
import com.service.dto.user.QueryUserImageOutDTO;
import com.service.inter.IUserImageService;
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
            url = "/user/queryUserImage",
            in = QueryUserImageInDTO.class,
            out = QueryUserImageOutDTO.class,
            name = "查询用户头像接口"
    )
    @Override
    public QueryUserImageOutDTO queryUserImage(QueryUserImageInDTO in) {
        QueryUserImageOutDTO out = new QueryUserImageOutDTO();
        Optional<UserImage> optional =  userImageMapper.queryByUserId(in.getUserId());
        optional.ifPresent(out::setUserImage);
        return out;
    }
}
