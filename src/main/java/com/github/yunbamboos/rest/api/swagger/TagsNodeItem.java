package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

public class TagsNodeItem {

    String name;

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
