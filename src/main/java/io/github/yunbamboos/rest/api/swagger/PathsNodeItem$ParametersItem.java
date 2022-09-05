package io.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;

public class PathsNodeItem$ParametersItem {

    String name;//image_id
    String in;// query  header
    String description;//用户头像ID
    boolean required = false;// true
    String example;
    JSONObject schema;

    public JSONObject getJSONObject(){
        JSONObject json = new JSONObject(new LinkedHashMap<>());
        json.put("name", name);
        json.put("in", in);
        json.put("description", description);
        json.put("required", required);
        json.put("example", example);
        json.put("schema", schema);
        return json;
    }

}
