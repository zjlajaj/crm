package com.zjl.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.zjl.bo.LoginData;
import com.zjl.exception.CustomerAuthenionException;

import java.util.Date;

/**
 * JSON Web Token 工具类
 *  pom依赖
 *  <dependency>
 *             <groupId>io.jsonwebtoken</groupId>
 *             <artifactId>jjwt-impl</artifactId>
 *             <version>0.10.7</version>
 *  </dependency>
 */
public class JWTUtil {

    /**
     * key（按照签名算法的字节长度设置key）
     */
    private final static String SECRET_KEY = "0123456789_0123456789_0123456789";
    /**
     * 过期时间（毫秒单位）
     */
    private final static long TOKEN_EXPIRE_MILLIS = 1000 * 60 * 60*24;

    /**
     * 创建token
     * @param loginData
     * @return
     */
    public static String createToken(LoginData loginData) {
        String token="";
        token= JWT.create()
                .withKeyId(String.valueOf(loginData.getId()))
                .withClaim("role",loginData.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRE_MILLIS))
                .sign(Algorithm.HMAC256(loginData.getPassword()));
        return token;
    }


    public static LoginData getLoginData(String token) throws CustomerAuthenionException {
        String role;
        Integer id;
        LoginData loginData = new LoginData();
        try {
            role = JWT.decode(token).getClaim("role").asString();
            id= Integer.valueOf(JWT.decode(token).getKeyId());
            loginData.setId(id);
            loginData.setRole(role);
        } catch (JWTDecodeException e) {
            throw new CustomerAuthenionException("验证令牌有误 或令牌已过期");
        }

        return loginData;
    }
}
