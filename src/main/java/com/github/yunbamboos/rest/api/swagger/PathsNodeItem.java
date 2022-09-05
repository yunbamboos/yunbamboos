package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

public class PathsNodeItem {

    String url;
    PathsNodeItem$Method method;


    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put(method.method(), method.getJSONObject());
        return json;
    }

}
