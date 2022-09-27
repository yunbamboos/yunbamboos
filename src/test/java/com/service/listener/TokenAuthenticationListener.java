package com.service.listener;

import io.github.yunbamboos.listener.AuthenticationListener;
import io.github.yunbamboos.listener.event.AuthenticationEvent;
import io.github.yunbamboos.session.SessionContext;
import io.github.yunbamboos.util.StringUtils;
import io.github.yunbamboos.util.TokenUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
/**
 * 权限认证
 *   验证token是否合法并解析token内容存储到SessionContext
 * */
@Component
public class TokenAuthenticationListener implements AuthenticationListener {

    private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationListener.class);

    @Override
    public boolean auth(AuthenticationEvent event) {
        HttpServletRequest request = event.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = TokenUtils.parseToken(token);
                SessionContext.set("user_id", claims.get("user_id", String.class));
                return true;
            } catch (Exception e) {
                log.error("token error", e);
            }
        }
        return false;
    }
}
