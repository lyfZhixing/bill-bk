package org.bill.core.service;

import org.bill.common.exception.ServiceException;
import org.bill.core.model.entity.Token;
import org.bill.core.model.entity.User;

/**
 * 登录服务
 *
 */
public interface AccountService {

    /**
     * 登录
     *
     * @param email 邮箱
     * @param password 密码
     * @return
     * @throws ServiceException
     */
    Token login(String email, String password) throws ServiceException;

    /**
     * 登出
     *
     * @param user 当前用户
     */
    void logout(User user);

    /**
     * 注册
     *
     * @param user 用户信息
     * @param seccode 验证码
     */
    void register(User user, String seccode) throws ServiceException;
}
