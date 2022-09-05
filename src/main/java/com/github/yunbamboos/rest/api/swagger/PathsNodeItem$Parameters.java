package com.github.yunbamboos.rest.api.swagger;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PathsNodeItem$Parameters {

    List<PathsNodeItem$ParametersItem> list;

    public PathsNodeItem$Parameters(){
        this.list = new ArrayList<>();
    }

    public JSONArray getJSONArray(){
        JSONArray array = new JSONArray();
        for (PathsNodeItem$ParametersItem item: list){
            array.add(item.getJSONObject());
        }
        return array;
    }

    public void add(PathsNodeItem$ParametersItem item){
        list.add(item);
    }
}
