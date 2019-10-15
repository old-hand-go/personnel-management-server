package com.oldhandgo.system.modules.system.repository;

import com.oldhandgo.system.modules.system.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
/*
* 根据id查询
* */
    Department findDepartmentById(long id);
}
