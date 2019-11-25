package org.bill.core.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Token 工具类
 *
 */
@Slf4j
public class TokenUtil {


    /**
     * 获取真实的toekn，去掉‘Bearer ’
     *
     * @param auth token
     * @return 真实token
     */
    public static String extractJwtTokenFromAuthorizationHeader(String auth) {
        // Replace "Bearer Token" to "Token" directly
        return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "")
                .replace(" ", "");
    }
}
