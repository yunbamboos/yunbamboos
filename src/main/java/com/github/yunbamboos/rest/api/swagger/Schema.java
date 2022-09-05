package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Schema {

    /**
     * 根节点
     */
    public static class Root {
        String title = "根目录";
        String type = "object";
        Properties properties;
        List<String> orders;
        List<String> requireds;

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject(new LinkedHashMap<>());
            json.put("title", title);
            json.put("type", type);
            if (properties != null) json.put("properties", properties.getJSONObject());
            if (orders != null) json.put("x-apifox-orders", orders);
            if (requireds != null) json.put("required", requireds);
            json.put("x-apifox-ignore-properties", new JSONArray());
            return json;
        }
    }

    public static class Properties {

        List<Attr> attrs = new ArrayList<>();

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject(new LinkedHashMap<>());
            for (Attr attr : attrs) {
                json.put(attr.name, attr.getJSONObject());
            }
            return json;
        }
    }

    public static class Attr {
        String name;
        String type;
        String title;
        String defaultValue;
        String description;
        boolean required;
        Properties properties;
        List<String> orders;
        List<String> requireds;

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject(new LinkedHashMap<>());
            json.put("type", type);
            json.put("title", title);
            json.put("default", defaultValue);
            json.put("description", description);
            if (properties != null) {
                json.put("properties", properties.getJSONObject());
            }
            if (orders != null) json.put("x-apifox-orders", orders);
            if (requireds != null) json.put("required", requireds);
            return json;
        }
    }
}
