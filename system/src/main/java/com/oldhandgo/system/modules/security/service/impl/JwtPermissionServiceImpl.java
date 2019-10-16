package com.oldhandgo.system.modules.security.service.impl;

import com.oldhandgo.system.modules.system.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * JWT权限类Impl
 *
 * @author dormirr
 */
@Service
public class JwtPermissionServiceImpl {
    private final RoleRepository roleRepository;

    public JwtPermissionServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
