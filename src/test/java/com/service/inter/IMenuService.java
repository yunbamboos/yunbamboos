package com.service.inter;

import com.service.dto.menu.QueryCurLoginUserMenuListInDTO;
import com.service.dto.menu.QueryCurLoginUserMenuListOutDTO;

/**
 * 菜单相关接口
 * */
public interface IMenuService {

    /**
     * 查询当前登录用户菜单列表
     *
     * @param in {link QueryCurLoginUserMenuListInDTO}
     * @return {link QueryCurLoginUserMenuListOutDTO}
     */
    QueryCurLoginUserMenuListOutDTO queryCurLoginUserMenuList(QueryCurLoginUserMenuListInDTO in);

}
