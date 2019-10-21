package com.oldhandgo.modules.security.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author dormirr
 */
@Getter
@AllArgsConstructor
public class AuthenticationInfo implements Serializable {

    private final String token;

    private final JwtUser user;
}
