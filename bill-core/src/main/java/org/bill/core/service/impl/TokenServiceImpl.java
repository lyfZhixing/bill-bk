package org.bill.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bill.common.cache.RedisCache;
import org.bill.common.constant.Constants;
import org.bill.core.model.entity.Token;
import org.bill.core.model.entity.User;
import org.bill.core.service.TokenService;
import org.bill.core.util.JWTUtil;
import org.bill.core.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisCache redisCache;

    @Override
    public Token creatToken(User user) {
        String subject = JWTUtil.generalSubject(user);
        long userId = user.getId();
        String token = JWTUtil.createJWT(userId, subject, Constants.JWT_TTL);
        Token model = new Token(userId, Constants.TOKEN_PREFIX + token);

        log.info("Create token, userId: {}", userId);
        redisCache.setEx(String.valueOf(userId), token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public void deleteToken(long userId) {
        log.info("Delete token, userId: {}", userId);
        redisCache.delete(String.valueOf(userId));
    }

    @Override
    public boolean checkToken(Token model) {
        Objects.requireNonNull(model);

        log.info("Check token, userId: {}", model.getUserId());
        String source = redisCache.get(String.valueOf(model.getUserId()));
        if (StringUtils.isEmpty(source) || !source.equals(model.getToken())) {
            return false;
        }
        // 延长过期时间
        redisCache.expire(String.valueOf(model.getUserId()), Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);

        return true;
    }

    @Override
    public Token getToken(String authorization) {
        if (authorization == null || "".equals(authorization)) {
            return null;
        }

        User user = TokenUtil.getUserFromToken(authorization);
        if (user == null) {
            return null;
        }

        long userId = user.getId();

        String token = TokenUtil.extractJwtTokenFromAuthorizationHeader(authorization);
        Token model = new Token(userId, token);
        return model;
    }
}
