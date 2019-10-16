package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.common.exception.EntityNotFoundException;
import com.oldhandgo.common.utils.ValidationUtils;
import com.oldhandgo.system.modules.system.domain.User;
import com.oldhandgo.system.modules.system.repository.UserRepository;
import com.oldhandgo.system.modules.system.service.UserService;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;
import com.oldhandgo.system.modules.system.service.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dormir
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findByEmailAndIsEnabled(String email, Byte isEnabled) {
        User user = null;
        if (ValidationUtils.isEmail(email)) {
            user = userRepository.findByEmailAndIsEnabled(email, isEnabled).orElse(null);
        }
        if (user != null) {
            return userMapper.userToUserDto(user);
        } else {
            throw new EntityNotFoundException(User.class, "邮箱", email);
        }
    }
}
