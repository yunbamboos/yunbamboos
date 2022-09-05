package io.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PathsNodeItem$Responses {

    Status ok; // 200

    public PathsNodeItem$Responses() {
        this.ok = new Status();
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("200", ok.getJSONObject());
        return json;
    }

    public static class Status {
        int code = 200;
        String description = "成功";
        Content content;

        public Status(){
            this.content = new Content();
        }

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject(new LinkedHashMap<>());
            json.put("code", code);
            json.put("description", description);
            json.put("content", content.getJSONObject());
            return json;
        }
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
        Schema.Attr data;
        JSONObject dataExample;

        public ApplicationJson() {
            this.init();
        }

        private void init(){
            this.root = new Schema.Root();
            this.root.properties = new Schema.Properties();
            this.root.orders = new ArrayList<>();
            this.root.requireds = new ArrayList<>();

            this.example = new JSONObject(new LinkedHashMap<>());

            Schema.Attr codeAttr = new Schema.Attr();
            codeAttr.name = "code";
            codeAttr.type = "integer";
            codeAttr.title = "返回编码";
            codeAttr.defaultValue = "200";
            codeAttr.description = "返回编码";
            codeAttr.required = true;
            this.root.properties.attrs.add(codeAttr);
            this.example.put(codeAttr.name, 200);
            this.root.orders.add(codeAttr.name);
            this.root.requireds.add(codeAttr.name);

            Schema.Attr msgAttr = new Schema.Attr();
            msgAttr.name = "msg";
            msgAttr.type = "string";
            msgAttr.title = "返回文字描述";
            msgAttr.defaultValue = "OK";
            msgAttr.description = "返回文字描述";
            msgAttr.required = true;
            this.root.properties.attrs.add(msgAttr);
            this.example.put(msgAttr.name, "OK");
            this.root.orders.add(msgAttr.name);
            this.root.requireds.add(msgAttr.name);

            Schema.Attr dataAttr = new Schema.Attr();
            dataAttr.name = "data";
            dataAttr.type = "object";
            dataAttr.title = "返回内容";
            dataAttr.defaultValue = "";
            dataAttr.description = "返回内容";
            dataAttr.required = true;
            this.root.properties.attrs.add(dataAttr);
            this.dataExample = new JSONObject();
            this.example.put(dataAttr.name, this.dataExample);
            this.root.orders.add(dataAttr.name);
            this.root.requireds.add(dataAttr.name);

            dataAttr.properties = new Schema.Properties();
            dataAttr.orders = new ArrayList<>();
            dataAttr.requireds = new ArrayList<>();
            data = dataAttr;
        }

        public JSONObject getJSONObject() {
            JSONObject json = new JSONObject();
            json.put("schema", root.getJSONObject());
            JSONObject examples = new JSONObject();
            JSONObject summary = new JSONObject();
            summary.put("summary", "成功示例");
            summary.put("value", example);
            examples.put("1",summary);
            json.put("examples", examples);
            return json;
        }
    }

}
