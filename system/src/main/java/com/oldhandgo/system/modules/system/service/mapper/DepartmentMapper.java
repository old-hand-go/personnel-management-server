package com.oldhandgo.system.modules.system.service.mapper;

import com.oldhandgo.system.modules.system.domain.Department;
import com.oldhandgo.system.modules.system.service.dto.DepartmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    /*
    *    DepartmentDTO  映射
    * */
    DepartmentDTO departmentDto(Department department);
}
