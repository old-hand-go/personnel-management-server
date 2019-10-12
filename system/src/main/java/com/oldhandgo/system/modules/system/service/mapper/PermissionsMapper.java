package com.oldhandgo.system.modules.system.service.mapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionsMapper {
    List userToPermissionDto(String uid);

}
