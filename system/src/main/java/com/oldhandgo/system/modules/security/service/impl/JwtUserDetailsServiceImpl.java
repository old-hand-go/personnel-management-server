package com.oldhandgo.system.modules.security.service.impl;

import com.oldhandgo.common.exception.BadRequestException;
import com.oldhandgo.system.modules.security.security.JwtUser;
import com.oldhandgo.system.modules.system.service.UserService;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * JWT用户详细类Impl
 *
 * @author dormirr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByEmailAndIsEnabled(userName, (byte) 1);
        if (userDTO == null) {
            throw new BadRequestException("账号不存在");
        } else {
            return createJwtUser(userDTO);
        }
    }

    public UserDetails createJwtUser(UserDTO userDTO) {
        return new JwtUser(
                userDTO.getId(),
                userDTO.getCreateTime(),
                userDTO.getUpdateTime(),
                userDTO.getEmail(),
                userDTO.getUserName(),
                userDTO.getPassWord(),
                userDTO.getAddress(),
                userDTO.getDeptId(),
                userDTO.getJobId(),
                userDTO.getIsEnabled()
        );
    }
}
