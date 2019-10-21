package com.oldhandgo.system.modules.security.service.impl;

import com.oldhandgo.system.modules.system.domain.Role;
import com.oldhandgo.system.modules.system.repository.RoleRepository;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dormirr
 */
@Service
@CacheConfig(cacheNames = "role")
public class JwtPermissionServiceImpl {

    private final RoleRepository roleRepository;

    public JwtPermissionServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * key的名称如有修改，同步修改 UserServiceImpl 中的 update 方法
     *
     * @param user 用户
     * @return 授予的权限
     */
    @Cacheable(key = "'loadPermissionByUser:' + #p0.username")
    public Collection<GrantedAuthority> mapToGrantedAuthorities(UserDTO user) {

        System.out.println("--------------------loadPermissionByUser:" + user.getUsername() + "---------------------");

        Set<Role> roles = roleRepository.findByUsers_Id(user.getId());

        return roles.stream().flatMap(role -> role.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }
}
