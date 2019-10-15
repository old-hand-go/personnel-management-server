package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.RolesMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author dormirr
 */
public interface RolesMenusRepository extends JpaRepository<RolesMenus, Long>, PagingAndSortingRepository<RolesMenus, Long> {

    /**
     * 根据角色ID查询所有部门
     *
     * @param roleId 角色ID
     * @return 部门ID
     */
    List<RolesMenus> findAllByRoleId(Long roleId);
}
