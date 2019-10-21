package com.oldhandgo.system.modules.security.service.impl;

import com.oldhandgo.common.exception.BadRequestException;
import com.oldhandgo.system.modules.security.security.JwtUser;
import com.oldhandgo.system.modules.system.service.UserService;
import com.oldhandgo.system.modules.system.service.dto.DeptSmallDTO;
import com.oldhandgo.system.modules.system.service.dto.JobSmallDTO;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * JWT用户详细类Impl
 *
 * @author dormirr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final JwtPermissionServiceImpl permissionService;

    public JwtUserDetailsServiceImpl(UserService userService, JwtPermissionServiceImpl permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByName(userName);
        if (userDTO == null) {
            throw new BadRequestException("账号不存在");
        } else {
            return createJwtUser(userDTO);
        }
    }

    public UserDetails createJwtUser(UserDTO user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                user.getPhone(),
                Optional.ofNullable(user.getDept()).map(DeptSmallDTO::getName).orElse(null),
                Optional.ofNullable(user.getJob()).map(JobSmallDTO::getName).orElse(null),
                permissionService.mapToGrantedAuthorities(user),
                user.getEnabled(),
                user.getCreateTime(),
                user.getLastPasswordResetTime()
        );
    }
}
