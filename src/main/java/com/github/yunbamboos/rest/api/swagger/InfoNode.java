package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

public class InfoNode {

    String title;
    String description;
    String version;

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject(new LinkedHashMap<>());
        json.put("title", title);
        json.put("description", description);
        json.put("version", version);
        return json;
    }
}
