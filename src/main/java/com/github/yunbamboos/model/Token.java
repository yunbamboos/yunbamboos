package com.github.yunbamboos.model;

import com.alibaba.fastjson.JSONObject;
import com.github.yunbamboos.Model;
import com.github.yunbamboos.rest.anno.ParamDesc;
import com.github.yunbamboos.rest.anno.ParamType;
import com.github.yunbamboos.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

public class Token implements Model {

    private final transient Claims claims;

    /**账户token*/
    @ParamDesc(name = "account_token", zn = "账户token", isNull = true, type = ParamType.String)
    private String accountToken;
    /**刷新账户token*/
    @ParamDesc(name = "refresh_token", zn = "刷新账户token", isNull = true, type = ParamType.String)
    private String refreshToken;

    public Token(){
        this.claims = new DefaultClaims();
    }

    public void set(String key, Object value){
        claims.put(key, String.valueOf(value));
    }

    public void createToken(){
        try{
            accountToken = TokenUtils.createToken(claims,TokenUtils.ACCOUNT_EXPIRE_TIME);
            refreshToken = TokenUtils.createToken(claims,TokenUtils.REFRESH_EXPIRE_TIME);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("account_token", accountToken);
        json.put("refresh_token", refreshToken);
        return json;
    }

}
