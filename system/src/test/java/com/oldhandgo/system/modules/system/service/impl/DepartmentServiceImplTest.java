package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.system.SystemApplication;
import com.oldhandgo.system.modules.system.domain.Department;
import com.oldhandgo.system.modules.system.repository.DepartmentRepository;
import com.oldhandgo.system.modules.system.service.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SystemApplication.class)
public class DepartmentServiceImplTest {
@Autowired
    DepartmentMapper departmentMapper;
@Autowired
DepartmentRepository departmentRepository;
    @Test
    public void findAll() {
        int id=1;
        Department department;
        department = departmentRepository.findDepartmentById(id);
        System.out.println(departmentMapper.departmentDto(department));
    }
}