package io.github.yunbamboos.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 所有数据传输类父类
 */
public interface DTO extends Serializable {

    default void decode(JSONObject json){
        // empty
    }

    default JSONObject encode(){
        return new JSONObject();
    }

    default String toJSON(){
        return encode().toJSONString();
    }
}
