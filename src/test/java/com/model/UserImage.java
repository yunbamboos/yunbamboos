package com.model;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.Base64Utils;


/**
 * user_image(用户头像表)
 */
public class UserImage implements Model {

    /** 用户ID(user_id) */
    @ParamDesc(name = "user_id", zn = "用户ID", isNull = true, type = ParamType.Integer, demo = "1")
    private int userId;
    /** 图像(image) */
    @ParamDesc(name = "image", zn = "图像", isNull = true, type = ParamType.String, demo = "base64字符串")
    private byte[] image;

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("image", Base64Utils.encode(image));
        return json;
    }

    @Override
    public void decode(JSONObject json){
        // empty
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
