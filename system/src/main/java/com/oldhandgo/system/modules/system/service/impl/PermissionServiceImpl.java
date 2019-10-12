package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.system.modules.system.domain.Permission;
import com.oldhandgo.system.modules.system.repository.PermissionRepository;
import com.oldhandgo.system.modules.system.repository.UserRepository;
import com.oldhandgo.system.modules.system.service.PermissionService;
import com.oldhandgo.system.modules.system.service.mapper.PermissionsMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "permission")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionsMapper permissionsMapper;

    public PermissionServiceImpl( PermissionRepository permissionRepository, PermissionsMapper permissionsMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionsMapper = permissionsMapper;
    }
    @Override
    @Cacheable(key = "#uid")
    public List findByUid(String uid) {

        List list =  permissionRepository.findByUid(uid);
        return list;
    }
}
