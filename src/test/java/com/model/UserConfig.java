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
    /** 配置group(group) */
    @ParamDesc(name = "group", zn = "配置group", isNull = true, type = ParamType.String, demo = "1")
    private String group;
    /** 配置key(key) */
    @ParamDesc(name = "key", zn = "配置key", isNull = true, type = ParamType.String, demo = "1")
    private String key;
    /** 配置值(value) */
    @ParamDesc(name = "value", zn = "配置值", isNull = true, type = ParamType.String, demo = "1")
    private String value;

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("group", group);
        json.put("key", key);
        json.put("value", value);
        return json;
    }

    @Override
    public void decode(JSONObject json){
        if(json.containsKey("user_id")) this.userId = json.getInteger("user_id");
        if(json.containsKey("group")) this.group = json.getString("group");
        if(json.containsKey("key")) this.key = json.getString("key");
        if(json.containsKey("value")) this.value = json.getString("value");
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
