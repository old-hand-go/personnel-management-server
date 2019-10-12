package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.system.modules.system.repository.RoleRepository;
import com.oldhandgo.system.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author dormir
 */
@Service
@CacheConfig(cacheNames = "Roles")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<RoleRepository> findById(Long id) {
        Optional<RoleRepository> role;
        role = roleRepository.findById(1L);
        return role;
    }
}
