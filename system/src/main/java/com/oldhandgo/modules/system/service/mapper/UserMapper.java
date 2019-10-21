package com.oldhandgo.modules.system.service.mapper;

import com.oldhandgo.mapper.EntityMapper;
import com.oldhandgo.modules.system.domain.User;
import com.oldhandgo.modules.system.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author dormirr
 */
@Mapper(componentModel = "spring", uses = {RoleMapper.class, DeptMapper.class, JobMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Override
    @Mapping(source = "user.userAvatar.realName", target = "avatar")
    UserDTO toDto(User user);
}
