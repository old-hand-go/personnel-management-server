package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.RolesDepartments;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dormirr
 */
public interface RolesDepartmentsRepository extends JpaRepository<RolesDepartments, Long> {
    /**
     * 根据角色ID查询部门ID
     *
     * @param roleId 角色ID
     * @return 部门ID
     */
    RolesDepartments findByRoleId(Long roleId);
}
