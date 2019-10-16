package com.oldhandgo.system.modules.security.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author dormirr
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {
    @JsonIgnore
    private final Long id;

    @JsonIgnore
    private final Timestamp createTime;

    @JsonIgnore
    private final Timestamp updateTime;

    private final String email;

    private final String userName;

    @JsonIgnore
    private final String passWord;

    private final String address;

    private final Long deptId;

    private final Long jobId;

    private final Byte isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled == 1;
    }
}