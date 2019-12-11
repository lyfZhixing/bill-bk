package org.bill.core.service;

import org.bill.core.model.entity.Token;
import org.bill.core.model.entity.User;

/**
 * Token服务接口
 *
 */
public interface TokenService {

    /**
     * Creates the token of authorization.
     *
     * @param user the user
     * @return The model of Token.
     */
    Token creatToken(User user);

    /**
     * Deteles the token of authorization.
     *
     * @param userId
     */
    void deleteToken(long userId);

    /**
     * Checks out the token that is from Redis.
     *
     * @param token
     * @return
     */
    boolean checkToken(Token token);

    /**
     * Gets the model of Token from authorization string.
     *
     * @param authorization
     * @return The model of Token.
     */
    Token getToken(String authorization);
}
