package com.oldhandgo.system.modules.security.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 认证信息类
 *
 * @author dormirr
 */
@Getter
@AllArgsConstructor
public class AuthenticationInfo implements Serializable {

    private final String token;

    private final JwtUser user;
}
