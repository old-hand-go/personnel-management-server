package com.oldhandgo.modules.security.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author dormirr
 */
@Getter
@Setter
public class AuthorizationUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

    @Override
    public String toString() {
        return "{username=" + username + ", password= ******}";
    }
}
