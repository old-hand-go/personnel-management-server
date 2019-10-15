package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 田禄
 */
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor {

    /**
     * 根据上级部门ID查询下属部门
     *
     * @param pid       上级部门ID
     * @param isEnabled 开启 必须为1
     * @return 查询结果
     */
    List<Department> findAllByPidAndIsEnabled(Long pid, Byte isEnabled);
}
