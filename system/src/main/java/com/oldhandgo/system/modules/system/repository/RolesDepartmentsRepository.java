package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.RolesDepartments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author dormirr
 */
public interface RolesDepartmentsRepository extends JpaRepository<RolesDepartments, Long>, PagingAndSortingRepository<RolesDepartments, Long> {

    /**
     * 根据角色ID查询全部部门ID
     *
     * @param roleId 角色ID
     * @return 结果
     */
    List<RolesDepartments> findAllByRoleId(Long roleId);
}
