package com.model;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

/**
 * user_config(用户配置表)
 */
public class UserConfig implements Model {

    /** 用户ID(user_id) */
    @ParamDesc(name = "user_id", zn = "用户ID", isNull = true, type = ParamType.Integer, demo = "1")
    private int userId;
    /** 配置属性(attr) */
    @ParamDesc(name = "attr", zn = "配置属性", isNull = true, type = ParamType.String, demo = "attr")
    private String attr;
    /** 配置属性值(value) */
    @ParamDesc(name = "value", zn = "配置属性值", isNull = true, type = ParamType.String, demo = "value")
    private String value;

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("attr", attr);
        json.put("value", value);
        return json;
    }

    @Override
    public void decode(JSONObject json){
        if(json.containsKey("user_id")) this.userId = json.getInteger("user_id");
        if(json.containsKey("attr")) this.attr = json.getString("attr");
        if(json.containsKey("value")) this.value = json.getString("value");
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
