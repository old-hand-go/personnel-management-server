package com.oldhandgo.system.modules.system.service.impl;
import com.oldhandgo.system.modules.system.repository.DepartmentRepository;
import com.oldhandgo.system.modules.system.service.DepartmentService;
import com.oldhandgo.system.modules.system.service.dto.DepartmentDTO;
import com.oldhandgo.system.modules.system.service.mapper.DepartmentMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "departments")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentServiceImpl( DepartmentRepository departmentRepository) {

        this.departmentRepository=departmentRepository;
    }

    @Override
    @Cacheable(key = "#id")
    public List<DepartmentService> findAll(long id) {
        return null;
    }

    @Override
    @Cacheable(key = "#departmentName")
    public DepartmentDTO addDepartment(String departmentName) {
        return null;
    }
}
