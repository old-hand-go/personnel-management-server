package com.oldhandgo.system.modules.system.service.mapper;

import com.oldhandgo.system.modules.system.domain.User;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;

import org.mapstruct.Mapper;


/**
 * @author dormir
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * UserDTO映射
     *
     * @param user User实体类
     * @return UserDTO映射
     */
    UserDTO userToUserDto(User user);
}
