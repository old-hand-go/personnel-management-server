package com.oldhandgo.system.modules.system.service.mapper;

import com.oldhandgo.system.modules.system.domain.User;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;

/**
 * @author dormir
 */
@Mapper
public interface UserMapper {
    /**
     * UserDTO映射
     *
     * @param user
     * @return
     */
    @MapMapping
    UserDTO userToUserDto(User user);
}
