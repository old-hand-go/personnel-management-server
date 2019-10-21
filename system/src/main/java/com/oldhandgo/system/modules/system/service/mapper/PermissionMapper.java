package com.oldhandgo.system.modules.system.service.mapper;

import com.oldhandgo.common.mapper.EntityMapper;
import com.oldhandgo.system.modules.system.domain.Permission;
import com.oldhandgo.system.modules.system.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

}
