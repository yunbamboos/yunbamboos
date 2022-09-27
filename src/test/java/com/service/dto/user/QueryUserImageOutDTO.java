package com.service.dto.user;

import com.alibaba.fastjson.JSONObject;
import com.model.UserImage;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.ObjectUtils;

public class QueryUserImageOutDTO extends OutDTO {

    @ParamDesc(name = "user_image", zn = "用户头像信息", isNull = true, type = ParamType.Object)
    private UserImage userImage;

    @Override
    public JSONObject encodeData() {
        JSONObject json = new JSONObject();
        if (ObjectUtils.isNotNull(userImage)) {
            json.put("user_image", userImage.encode());
        }
        return json;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }
}
