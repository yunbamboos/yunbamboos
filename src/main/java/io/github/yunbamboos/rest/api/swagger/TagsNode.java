package io.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagsNode {

    Map<String, TagsNodeItem> map;
    List<TagsNodeItem> list;

    public TagsNode() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public JSONArray getJSONArray() {
        JSONArray array = new JSONArray();
        for (TagsNodeItem item : list) {
            array.add(item.getJSONObject());
        }
        return array;
    }

    public void add(TagsNodeItem item) {
        if (!map.containsKey(item.name)) {
            list.add(item);
            map.put(item.name, item);
        }
    }

}
