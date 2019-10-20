package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author 田禄
 * @author dormirr
 */
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor {
    /**
     * 根据上级部门ID查询下属部门
     *
     * @param pid 上级部门ID
     * @return 查询结果
     */
    List<Department> findByPid(Long pid);

    /**
     * @param id ID
     * @return 部门名称
     */
    @Query(value = "select department_name from personnel_management_server.department where id = ?1", nativeQuery = true)
    String findNameById(Long id);

    Set<Department> findByRoles_Id(Long id);
}