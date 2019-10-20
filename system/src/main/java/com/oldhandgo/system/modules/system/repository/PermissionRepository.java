package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author dormirr
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor {

    /**
     * 根据权限名称查询信息
     *
     * @param permissionName 权限名称
     * @return 权限表
     */
    Optional<Permission> findByPermissionName(String permissionName);

    /**
     * 根据上级权限查询下级权限
     *
     * @param pid 上级权限ID
     * @return 下级权限
     */
    List<Permission> findAllByPid(long pid);
}
