package io.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;

public class PathsNodeItem$Method {

    RequestMethod method;
    String summary;
    String folder;
    boolean deprecated;
    String description;
    JSONArray tags = new JSONArray();
    PathsNodeItem$Parameters parameters = new PathsNodeItem$Parameters();
    PathsNodeItem$RequestBody requestBody = new PathsNodeItem$RequestBody();
    PathsNodeItem$Responses responses = new PathsNodeItem$Responses();

    public PathsNodeItem$Method(RequestMethod method){
        this.method=method;
    }

    public String method(){
        if(RequestMethod.POST == method){
            return "post";
        }
        if(RequestMethod.GET == method){
            return "get";
        }
        return "post";
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject(new LinkedHashMap<>());
        json.put("summary", summary);
        json.put("x-apifox-folder", folder);
        json.put("x-apifox-status", "developing");
        json.put("deprecated", deprecated);
        json.put("description", description);
        json.put("tags", tags);
        json.put("parameters", parameters.getJSONArray());
        json.put("requestBody", requestBody.getJSONObject());
        json.put("responses", responses.getJSONObject());
        return json;
    }
}
