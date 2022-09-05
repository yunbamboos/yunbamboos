package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

public class PathsNodeItem$RequestBody {

    Content content;

    public PathsNodeItem$RequestBody() {
        this.content = new Content();
    }


    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("content", content.getJSONObject());
        return json;
    }

    public static class Content {

        ApplicationJson applicationJson;

        public Content() {
            this.applicationJson = new ApplicationJson();
        }

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject();
            json.put("application/json", applicationJson.getJSONObject());
            return json;
        }
    }

    public static class ApplicationJson {

        Schema.Root root;
        JSONObject example;

        public ApplicationJson() {
        }

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject();
            json.put("schema", root.getJSONObject());
            json.put("example", example);
            return json;
        }
    }

}
