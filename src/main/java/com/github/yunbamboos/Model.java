package com.github.yunbamboos;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
/**
 * 所有数据模型基类
 * */
public interface Model extends Serializable{
	
	default JSONObject encode() {
		return new JSONObject();
	}

	default void decode(JSONObject json){

	}

	default String toJSON(){
		return encode().toJSONString();
	}


}
