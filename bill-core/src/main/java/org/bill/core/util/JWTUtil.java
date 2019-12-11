package org.bill.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bill.common.constant.Constants;
import org.bill.core.model.entity.User;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * Token工具类
 *
 */
public class JWTUtil {

    /**
     *
     * 编码
     * String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
     *
     * 解码
     * byte[] asBytes = Base64.getDecoder().decode("c29tZSBzdHJpbmc=");
     * System.out.println(new String(asBytes, "utf-8"));
     */

    /**
     * 获得加密的key
     * @return 加密后的key
     */
    public static SecretKey generateKey() {
        byte[] encodedKey = Base64.getDecoder().decode(Constants.JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 签发 JWT
     * @param id
     * @param subject
     * @param ttlMillis
     * @return 生成的JWT token
     */
    public static String createJWT(Long id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generateKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id.toString()) // JWT_ID
                .setSubject(subject) // 主题
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            // 设置过期时间
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generateKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 生成subject信息
     * @param user
     * @return
     */
    public static String generalSubject(User user){
        JSONObject jo = new JSONObject();
        jo.put("id", user.getId());
        jo.put("name", user.getName());
        jo.put("loginName", user.getLoginName());
        return jo.toJSONString();
    }

}
