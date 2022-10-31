package com.service.impl.menu;

import com.service.dto.menu.QueryCurLoginUserMenuListInDTO;
import com.service.dto.menu.QueryCurLoginUserMenuListOutDTO;
import com.service.inter.IMenuService;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.anno.Tag;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 菜单相关接口实现
 */
@Tag("菜单相关接口")
@Service("MenuServiceImpl")
public class MenuServiceImpl implements IMenuService {

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/menu/queryCurLoginUserMenuList",
            in = QueryCurLoginUserMenuListInDTO.class,
            out = QueryCurLoginUserMenuListOutDTO.class,
            name = "查询当前登录用户菜单列表"
    )
    @Override
    public QueryCurLoginUserMenuListOutDTO queryCurLoginUserMenuList(QueryCurLoginUserMenuListInDTO in) {
        return null;
    }
}
