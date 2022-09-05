package com.github.yunbamboos.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * 登录Token的生成和解析
 */
public class TokenUtils {

    // Token的唯一身份标识
    private static final String ID = UUID.randomUUID().toString();
    // 加密密文，私钥
    private static final String SECRET = "yun";
    // 过期时间，单位毫秒
    public static final int ACCOUNT_EXPIRE_TIME = 60 * 60 * 1000;

    public static final int REFRESH_EXPIRE_TIME = 65 * 60 * 1000;

    // Token签发者
    private static final String ISSUER = "yun";

    private TokenUtils() {
    }

    // 由字符串生成加密key
    private static SecretKey generalKey() {
        // 本地的密码解码
        byte[] encodedKey = Base64.getEncoder().encode(SECRET.getBytes());
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 创建Token
     */
    public static String createToken(Claims claims, long expireTime) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成Token的时间
        long nowTime = System.currentTimeMillis();
        Date issuedAt = new Date(nowTime);
        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露，是你服务端的私钥，在任何场景都不应该流露出去，一旦客户端得知这个secret，那就意味着客户端是可以自我签发Token的
        SecretKey key = generalKey();
        // 为payload添加各种标准声明和私有声明
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims) // 如果有私有声明，一定要先设置自己创建的这个私有声明，这是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明
                .setId(ID) // Token的唯一身份标识，根据业务需要，可以设置为一个不重复的值，主要用来作为一次性token，从而回避重放攻击
                .setIssuedAt(issuedAt) // Token的签发时间
                .setIssuer(ISSUER) // Token签发者
                .signWith(signatureAlgorithm, key); // 设置签名，使用的是签名算法和签名使用的秘钥
        // 设置过期时间
        long exp = nowTime + expireTime * 60 * 1000;
        builder.setExpiration(new Date(exp));
        return builder.compact();
    }

    /**
     * 解密Token
     */
    public static Claims parseToken(String token) {
        SecretKey key = generalKey(); // 签名秘钥，和生成的签名的秘钥一模一样
        return Jwts.parser() // 得到DefaultJwtParser
                .setSigningKey(key) // 设置签名的秘钥
                .parseClaimsJws(token).getBody(); // 设置需要解析的Token
    }

    /**
     * 判断token是否快过去  使用时间 大于 70% expireTime
     * issueAt token签发日期
     */
    public static boolean tokenIsExpire(Date issueAt, long expireTime) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        if (LocalDateTime.now().minusMinutes(expireTime).isAfter(issueTime)) {
            return true;
        }
        return LocalDateTime.now().isAfter(issueTime.plusMinutes(expireTime * 7 / 10));// token 过期
    }

}

