package com.oldhandgo.personnelmanagementserver.service.mapper;

import com.oldhandgo.personnelmanagementserver.domain.User;
import com.oldhandgo.personnelmanagementserver.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author dormir
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * DTO映射
     *
     * @param user
     * @return
     */
    UserDTO userToUserDto(User user);
}
