package com.oldhandgo.system.modules.security.utils;

import com.oldhandgo.system.modules.security.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author dormirr
 */
@Component
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;
    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * 从令牌获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从令牌获取创建日期
     *
     * @param token 令牌
     * @return 创建日期
     */
    private Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * 从令牌获取到期日期
     *
     * @param token 令牌
     * @return 到期日期
     */
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 从令牌获取所有声明
     *
     * @param token          令牌
     * @param claimsResolver 声明解析器
     * @param <T>            声明类型
     * @return 声明
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从令牌获取所有声明
     *
     * @param token 令牌
     * @return 声明
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 令牌是否过期
     *
     * @param token 令牌
     * @return 结果
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    /**
     * token创建日期是否大于最后修改的日期
     *
     * @param created 创建时间
     * @param updated 最后修改时间
     * @return 结果
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date updated) {
        return (updated != null && created.before(updated));
    }

    /**
     * 是否忽略令牌
     *
     * @param token 令牌
     * @return 结果
     */
    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    /**
     * 生成令牌
     *
     * @param userDetails 用户详细信息
     * @return 令牌
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * 生成令牌
     *
     * @param claims  声明
     * @param subject 主题（用户名）
     * @return 令牌
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 令牌是否需要刷新
     *
     * @param token             令牌
     * @param lastPasswordReset 上次密码重置时间
     * @return 结果
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    /**
     * 更新令牌
     *
     * @param token 令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户详细信息
     * @return 令牌是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser jwtUser = (JwtUser) userDetails;
        final Date created = getIssuedAtDateFromToken(token);
        return (!isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, jwtUser.getLastPasswordResetDate())
        );
    }

    /**
     * 计算到期时间
     *
     * @param createdDate 创建时间
     * @return 到期时间
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration);
    }
}
