package com.github.yunbamboos.dto.out;

import com.alibaba.fastjson.JSONObject;
import com.github.yunbamboos.constant.ContentType;
import com.github.yunbamboos.dto.DTO;
import com.github.yunbamboos.rest.anno.ParamDesc;
import com.github.yunbamboos.rest.anno.ParamType;

/**
 * 服务出参封装基类
 * */
public abstract class OutDTO implements DTO {

    protected ContentType contentType;

    @ParamDesc(name = "code", zn = "返回编码", isNull = true, type = ParamType.Integer, demo = "200")
    protected int code = 200;

    @ParamDesc(name = "msg", zn = "返回描述", isNull = true, type = ParamType.String, demo = "OK")
    protected String msg = "OK";

    protected OutDTO(){
        this.contentType = ContentType.APPLICATION_JSON;
    }

    @Override
    public final JSONObject encode(){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", this.encodeData());
        return json;
    }

    public abstract JSONObject encodeData();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
