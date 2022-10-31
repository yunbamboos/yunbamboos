package com.service.listener;

import com.token.TokenCache;
import io.github.yunbamboos.listener.AuthenticationListener;
import io.github.yunbamboos.listener.event.AuthenticationEvent;
import io.github.yunbamboos.model.Token;
import io.github.yunbamboos.session.SessionContext;
import io.github.yunbamboos.util.StringUtils;
import io.github.yunbamboos.util.TokenUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 权限认证
 *   验证token是否合法并解析token内容存储到SessionContext
 * */
@Component
public class TokenAuthenticationListener implements AuthenticationListener {

    private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationListener.class);

    @Resource
    private TokenCache tokenCache;

    @Override
    public boolean auth(AuthenticationEvent event) {
        HttpServletRequest request = event.getRequest();
        String tokenId = request.getHeader("token");
        if(StringUtils.isEmpty(tokenId)){
            tokenId = request.getParameter("token");
        }
        Optional<Token> optional = tokenCache.get(tokenId);
        if(optional.isPresent()){
            Token token = optional.get();
            try {
                Claims claims = TokenUtils.parseToken(token.getAccountToken());
                SessionContext.set("user_id", claims.get("user_id", String.class));
                return true;
            } catch (Exception e) {
                log.error("token error", e);
            }
        }
        return false;
    }
}
