package com.oldhandgo.system.modules.security.rest;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.oldhandgo.common.utils.EncryptUtils;
import com.oldhandgo.common.utils.SecurityUtils;
import com.oldhandgo.system.modules.security.security.AuthenticationInfo;
import com.oldhandgo.system.modules.security.security.AuthorizationUser;
import com.oldhandgo.system.modules.security.security.ImgResult;
import com.oldhandgo.system.modules.security.security.JwtUser;
import com.oldhandgo.system.modules.security.service.impl.JwtUserDetailsServiceImpl;
import com.oldhandgo.system.modules.security.utils.JwtTokenUtils;
import com.oldhandgo.system.modules.security.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

/**
 * 安全认证控制器
 *
 * @author dormirr
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Value("${jwt.header}")
    private String tokenHeader;
    private final JwtTokenUtils jwtTokenUtils;
    private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    public AuthenticationController(JwtTokenUtils jwtTokenUtils, JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
    }

    /**
     * 登录授权
     *
     * @param authorizationUser 授权用户
     * @return 授权
     */
    @PostMapping(value = "${jwt.auth.path}")
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser) {
//        // 查询验证码
//        String code = redisService.getCodeVal(authorizationUser.getUuid());
//        // 清除验证码
//        redisService.delete(authorizationUser.getUuid());
//        if (StringUtils.isBlank(code)) {
//            throw new BadRequestException("验证码已过期");
//        }
//        if (StringUtils.isBlank(authorizationUser.getCode()) || !authorizationUser.getCode().equalsIgnoreCase(code)) {
//            throw new BadRequestException("验证码错误");
//        }
        final JwtUser jwtUser = (JwtUser) jwtUserDetailsServiceImpl.loadUserByUsername(authorizationUser.getUsername());
        if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))) {
            throw new AccountExpiredException("密码错误");
        }
        if (!jwtUser.isEnabled()) {
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }
        // 生成令牌
        final String token = jwtTokenUtils.generateToken(jwtUser);
        // 返回 token
        return ResponseEntity.ok(new AuthenticationInfo(token, jwtUser));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping(value = "${jwt.auth.account}")
    public ResponseEntity getUserInfo() {
        JwtUser jwtUser = (JwtUser) jwtUserDetailsServiceImpl.loadUserByUsername(SecurityUtils.getUserName());
        return ResponseEntity.ok(jwtUser);
    }

    /**
     * 获取验证码
     *
     * @param response response
     * @return 验证码图片
     */
    @GetMapping(value = "vCode")
    public ImgResult getCode(HttpServletResponse response) {
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtil.simpleUUID();
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (stream) {
            VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
            return new ImgResult(Base64.encode(stream.toByteArray()), uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
