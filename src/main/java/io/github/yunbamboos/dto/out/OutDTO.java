package io.github.yunbamboos.dto.out;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.constant.ContentType;
import io.github.yunbamboos.dto.DTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

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

    protected OutDTO(ContentType contentType){
        this.contentType = contentType;
    }

    @Override
    public final JSONObject encode(){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", this.encodeData());
        return json;
    }

    public JSONObject encodeData(){
        return new JSONObject();
    }

    public byte[] encodeBytes(){
        return new byte[0];
    }

    public final int getCode() {
        return code;
    }

    public final void setCode(int code) {
        this.code = code;
    }

    public final String getMsg() {
        return msg;
    }

    public final void setMsg(String msg) {
        this.msg = msg;
    }

    public final ContentType getContentType() {
        return contentType;
    }

    public final void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
