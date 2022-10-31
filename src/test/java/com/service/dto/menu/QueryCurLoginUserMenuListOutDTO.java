package com.service.dto.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.Menu;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;

import java.util.List;

public class QueryCurLoginUserMenuListOutDTO extends OutDTO {

    @ParamDesc(name = "menu_list", zn = "菜单列表", isNull = true, type = ParamType.List)
    private List<Menu> menuList;

    @Override
    public JSONObject encodeData() {
        JSONObject json = new JSONObject();
        if (ObjectUtils.isNotNull(menuList)) {
            JSONArray menuList = new JSONArray(this.menuList.size());
            for (Menu menu : this.menuList) {
                menuList.add(menu.encode());
            }
            json.put("menu_list", menuList);
        }
        return json;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
