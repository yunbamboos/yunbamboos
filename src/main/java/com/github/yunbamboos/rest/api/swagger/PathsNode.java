package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PathsNode {

    List<String> urls;
    Map<String, PathsNodeItem> map;

    public PathsNode() {
        this.urls = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject(new LinkedHashMap<>());
        for (String url : urls) {
            json.put(url, map.get(url).getJSONObject());
        }
        return json;
    }

    public void add(PathsNodeItem item) {
        if (!map.containsKey(item.url)) {
            urls.add(item.url);
            map.put(item.url, item);
        } else {
            throw new RuntimeException(item.url + " 已存在");
        }
    }

}
