package com.service.dto.user;

import com.model.UserImage;
import io.github.yunbamboos.constant.ContentType;
import io.github.yunbamboos.dto.out.OutDTO;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;

public class QueryUserImageOutDTO extends OutDTO {

    @ParamDesc(name = "user_image", zn = "用户头像信息", isNull = true, type = ParamType.Object)
    private UserImage userImage;

    public QueryUserImageOutDTO(ContentType contentType){
        super(contentType);
    }

    @Override
    public byte[] encodeBytes() {
        return userImage.getImage();
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }


}
