package com.oldhandgo.system.modules.system.service;

import com.oldhandgo.system.modules.system.service.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    /*
    * 根据id查询部门
    * */
    DepartmentDTO findAll(long id);

    /*
    * 新增部门
    * */
    DepartmentDTO addDepartment(String departmentName);
    /*
    *
    * */
}
