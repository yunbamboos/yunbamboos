package io.github.yunbamboos.model;

import com.alibaba.fastjson.JSONObject;
import io.github.yunbamboos.Model;
import io.github.yunbamboos.rest.anno.ParamDesc;
import io.github.yunbamboos.rest.anno.ParamType;
import io.github.yunbamboos.util.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

public class Token implements Model {

    private static final long ACCOUNT_EXPIRE_TIME = 60*60*1000L;

    private final transient Claims claims;

    @ParamDesc(name = "token_id", zn = "token_id", isNull = true, type = ParamType.String)
    private String tokenId;
    // 当前token创建时间
    private long createTime;
    // 当前token
    private String accountToken;

    public Token() {
        this.claims = new DefaultClaims();
    }

    public void set(String key, Object value) {
        claims.put(key, String.valueOf(value));
    }

    public void createToken() {
        try {
            this.createTime = System.currentTimeMillis();
            this.accountToken = TokenUtils.createToken(claims, createTime, ACCOUNT_EXPIRE_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExpire() {
        return (System.currentTimeMillis() - createTime) > ACCOUNT_EXPIRE_TIME;
    }

    @Override
    public JSONObject encode() {
        JSONObject json = new JSONObject();
        json.put("token_id", tokenId);
        return json;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAccountToken() {
        return accountToken;
    }
}
