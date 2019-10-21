package com.oldhandgo.modules.system.service.mapper;

import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.modules.system.domain.Role;
import com.oldhandgo.modules.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {PermissionMapper.class, MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
