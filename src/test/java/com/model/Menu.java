package com.model;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

import java.util.List;

/**
 * menu(菜单表)
 */
public class Menu implements Model {

    /** 菜单ID(menu_id) */
    @ParamDesc(name = "menu_id", zn = "菜单ID", isNull = true, type = ParamType.String, demo = "100000")
    private String menuId;
    /** 菜单名称(name) */
    @ParamDesc(name = "name", zn = "菜单名称", isNull = true, type = ParamType.String, demo = "home")
    private String name;
    /** 菜单路径(path) */
    @ParamDesc(name = "path", zn = "菜单路径", isNull = true, type = ParamType.String, demo = "/home")
    private String path;
    /** 菜单组件(component) */
    @ParamDesc(name = "component", zn = "菜单组件", isNull = true, type = ParamType.String, demo = "/home/index.vue")
    private String component;
    /** 菜单标题(title) */
    @ParamDesc(name = "title", zn = "菜单标题", isNull = true, type = ParamType.String, demo = "首页")
    private String title;
    /** 外部链接(link) */
    @ParamDesc(name = "link", zn = "外部链接", isNull = true, type = ParamType.String, demo = "/")
    private String link;
    /** 是否隐藏(hide) */
    @ParamDesc(name = "hide", zn = "是否隐藏", isNull = true, type = ParamType.Boolean, demo = "false")
    private boolean hide;
    /** 是否缓存(cache) */
    @ParamDesc(name = "cache", zn = "是否缓存", isNull = true, type = ParamType.Boolean, demo = "false")
    private boolean cache;
    /** 是否独立窗口(iframe) */
    @ParamDesc(name = "iframe", zn = "是否独立窗口", isNull = true, type = ParamType.Boolean, demo = "false")
    private boolean iframe;
    /** 菜单图标(icon) */
    @ParamDesc(name = "icon", zn = "菜单图标", isNull = true, type = ParamType.String, demo = "home")
    private String icon;
    /** 上级菜单ID(parent_menu_id) */
    @ParamDesc(name = "parent_menu_id", zn = "上级菜单ID", isNull = true, type = ParamType.String, demo = "0")
    private String parentMenuId;

    private List<Menu> children;

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("menu_id", menuId);
        json.put("name", name);
        json.put("path", path);
        json.put("component", component);
        json.put("title", title);
        json.put("link", link);
        json.put("hide", hide);
        json.put("cache", cache);
        json.put("iframe", iframe);
        json.put("icon", icon);
        json.put("parent_menu_id", parentMenuId);
        return json;
    }

    @Override
    public void decode(JSONObject json){
        if(json.containsKey("menu_id")) this.menuId = json.getString("menu_id");
        if(json.containsKey("name")) this.name = json.getString("name");
        if(json.containsKey("path")) this.path = json.getString("path");
        if(json.containsKey("component")) this.component = json.getString("component");
        if(json.containsKey("title")) this.title = json.getString("title");
        if(json.containsKey("link")) this.link = json.getString("link");
        if(json.containsKey("hide")) this.hide = json.getBoolean("hide");
        if(json.containsKey("cache")) this.cache = json.getBoolean("cache");
        if(json.containsKey("iframe")) this.iframe = json.getBoolean("iframe");
        if(json.containsKey("icon")) this.icon = json.getString("icon");
        if(json.containsKey("parent_menu_id")) this.parentMenuId = json.getString("parent_menu_id");
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public boolean isIframe() {
        return iframe;
    }

    public void setIframe(boolean iframe) {
        this.iframe = iframe;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
