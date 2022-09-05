package io.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

public class Swagger {

    String openapi = "3.0.1";

    InfoNode info;

    TagsNode tags;

    PathsNode paths;

    Swagger() {
        this.info = new InfoNode();
        this.tags = new TagsNode();
        this.paths = new PathsNode();
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject(new LinkedHashMap<>());
        json.put("openapi", openapi);
        json.put("info", info.getJSONObject());
        json.put("tags", tags.getJSONArray());
        json.put("paths", paths.getJSONObject());
        return json;
    }

}
