package com.github.yunbamboos.dto.out;

import com.alibaba.fastjson.JSONObject;
import com.github.yunbamboos.exception.AppException;
import com.github.yunbamboos.exception.ErrorCode;

public class ErrorOutDTO extends OutDTO {

    public ErrorOutDTO(Exception exception) {
        this(500, exception.getMessage());
    }

    public ErrorOutDTO(AppException appException) {
        this(appException.getCode(), appException.getMsg());
    }

    public ErrorOutDTO(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    public ErrorOutDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public JSONObject encodeData() {
        return null;
    }
}
