package com.service.impl.menu;

import com.mapper.MenuMapper;
import com.model.Menu;
import com.service.dto.menu.QueryCurLoginUserMenuListInDTO;
import com.service.dto.menu.QueryCurLoginUserMenuListOutDTO;
import com.service.inter.IMenuService;
import io.github.yunbamboos.rest.anno.RestServiceType;
import io.github.yunbamboos.rest.anno.Tag;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单相关接口实现
 */
@Tag("菜单相关接口")
@Service("MenuServiceImpl")
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @RestServiceType(
            method = RequestMethod.POST,
            url = "/menu/queryCurLoginUserMenuList",
            in = QueryCurLoginUserMenuListInDTO.class,
            out = QueryCurLoginUserMenuListOutDTO.class,
            name = "查询当前登录用户菜单列表"
    )
    @Override
    public QueryCurLoginUserMenuListOutDTO queryCurLoginUserMenuList(QueryCurLoginUserMenuListInDTO in) {
        QueryCurLoginUserMenuListOutDTO out = new QueryCurLoginUserMenuListOutDTO();
        List<Menu> menuList = menuMapper.queryAll();
        Map<String, List<Menu>> menuByParentIdMap = menuList.stream().collect(Collectors.groupingBy(Menu::getParentMenuId));
        menuList.forEach(menu -> menu.setChildren(menuByParentIdMap.get(menu.getMenuId())));
        out.setMenuList(menuList.stream().filter(menu -> "0".equals(menu.getParentMenuId())).collect(Collectors.toList()));
        return out;
    }
}
