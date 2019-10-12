package com.oldhandgo.system.modules.system.service;

import com.oldhandgo.system.modules.system.repository.RoleRepository;

import java.util.Optional;

/**
 * @author dormir
 */
public interface RoleService {
    /**
     * 根据ID 查询拥有的权限
     *
     * @param id 用户ID
     * @return 用户权限
     */
    Optional<RoleRepository> findById(Long id);

}
