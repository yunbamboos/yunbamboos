package com.service.inter;

import com.service.dto.user.QueryCurLoginUserConfigInDTO;
import com.service.dto.user.QueryCurLoginUserConfigOutDTO;

/**
 * 用户相关接口
 */
public interface IUserConfigService {

    /**
     * 查询当前登录用户配置接口
     *
     * @param in {link QueryUserConfigInDTO}
     * @return {link QueryUserConfigOutDTO}
     */
    QueryCurLoginUserConfigOutDTO queryCurLoginUserConfig(QueryCurLoginUserConfigInDTO in);

}
