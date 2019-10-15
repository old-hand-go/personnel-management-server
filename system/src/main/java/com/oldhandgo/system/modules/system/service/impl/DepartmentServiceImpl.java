package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.system.modules.system.domain.Department;
import com.oldhandgo.system.modules.system.repository.DepartmentRepository;
import com.oldhandgo.system.modules.system.service.DepartmentService;
import com.oldhandgo.system.modules.system.service.dto.DepartmentDTO;
import com.oldhandgo.system.modules.system.service.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "departments")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {

        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @Cacheable(key = "#id")
    public DepartmentDTO findAll(long id) {
        Department department;
        department = departmentRepository.findDepartmentById(id);
        return departmentMapper.departmentDto(department);
    }

    @Override
    @Cacheable(key = "#departmentName")
    public DepartmentDTO addDepartment(String departmentName) {
        return null;
    }
}
