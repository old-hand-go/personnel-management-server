package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.common.exception.EntityExistException;
import com.oldhandgo.common.utils.PageUtils;
import com.oldhandgo.common.utils.QueryHelp;
import com.oldhandgo.common.utils.ValidationUtils;
import com.oldhandgo.system.modules.system.domain.Role;
import com.oldhandgo.system.modules.system.repository.RoleRepository;
import com.oldhandgo.system.modules.system.service.RoleService;
import com.oldhandgo.system.modules.system.service.dto.RoleDTO;
import com.oldhandgo.system.modules.system.service.dto.RoleQueryCriteria;
import com.oldhandgo.system.modules.system.service.dto.RoleSmallDTO;
import com.oldhandgo.system.modules.system.service.mapper.RoleMapper;
import com.oldhandgo.system.modules.system.service.mapper.RoleSmallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dormirr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleSmallMapper roleSmallMapper;

    @Override
    public Object queryAll(Pageable pageable) {
        return roleMapper.toDto(roleRepository.findAll(pageable).getContent());
    }

    @Override
    public List<RoleDTO> queryAll(RoleQueryCriteria criteria) {
        return roleMapper.toDto(roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public Object queryAll(RoleQueryCriteria criteria, Pageable pageable) {
        Page<Role> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtils.toPage(page.map(roleMapper::toDto));
    }

    @Override
    public RoleDTO findById(long id) {
        Optional<Role> role = roleRepository.findById(id);
        ValidationUtils.isNull(role, "Role", "id", id);
        return roleMapper.toDto(role.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleDTO create(Role resources) {
        if (roleRepository.findByRoleName(resources.getRoleName()) != null) {
            throw new EntityExistException(Role.class, "username", resources.getRoleName());
        }
        return roleMapper.toDto(roleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role resources) {

        Optional<Role> optionalRole = roleRepository.findById(resources.getId());
        ValidationUtils.isNull(optionalRole, "Role", "id", resources.getId());

        Role role = optionalRole.get();

        Role role1 = roleRepository.findByRoleName(resources.getRoleName());

        if (role1 != null && !role1.getId().equals(role.getId())) {
            throw new EntityExistException(Role.class, "username", resources.getRoleName());
        }

        role.setRoleName(resources.getRoleName());
        role.setRemark(resources.getRemark());
        role.setDataScope(resources.getDataScope());
        role.setDepartments(resources.getDepartments());
        role.setRoleLevel(resources.getRoleLevel());
        roleRepository.save(role);
    }

    @Override
    public void updatePermission(Role resources, RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        role.setPermissions(resources.getPermissions());
        roleRepository.save(role);
    }

    @Override
    public void updateMenu(Role resources, RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        role.setMenus(resources.getMenus());
        roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void untiedMenu(Long id) {
        roleRepository.untiedMenu(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void untiedPermission(Long id) {
        roleRepository.untiedPermission(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleSmallDTO> findByUsers_Id(Long id) {
        return roleSmallMapper.toDto(roleRepository.findByUsers_Id(id).stream().collect(Collectors.toList()));
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        Set<RoleDTO> roleDTOS = new HashSet<>();
        for (Role role : roles) {
            roleDTOS.add(findById(role.getId()));
        }
        return Collections.min(roleDTOS.stream().map(RoleDTO::getLevel).collect(Collectors.toList()));
    }
}