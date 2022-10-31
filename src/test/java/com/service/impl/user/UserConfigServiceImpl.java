package com.service.impl.user;

import com.mapper.UserConfigMapper;
import com.mapper.UserImageMapper;
import com.model.UserConfig;
import com.service.dto.user.QueryCurLoginUserConfigInDTO;
import com.service.dto.user.QueryCurLoginUserConfigOutDTO;
import com.service.inter.IUserConfigService;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.anno.Tag;
import io.github.yunbamboos.session.SessionContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关接口实现
 */
@Tag("用户相关接口")
@Service("UserConfigServiceImpl")
public class UserConfigServiceImpl implements IUserConfigService {

    @Resource
    private UserConfigMapper userConfigMapper;

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/user/queryCurLoginUserConfig",
            in = QueryCurLoginUserConfigInDTO.class,
            out = QueryCurLoginUserConfigOutDTO.class,
            name = "查询当前登录用户配置接口"
    )
    @Override
    public QueryCurLoginUserConfigOutDTO queryCurLoginUserConfig(QueryCurLoginUserConfigInDTO in) {
        QueryCurLoginUserConfigOutDTO out = new QueryCurLoginUserConfigOutDTO();
        int userId = SessionContext.getInt("user_id");
        List<UserConfig> userConfigList = userConfigMapper.queryByUserId(userId);

        return out;
    }
}
