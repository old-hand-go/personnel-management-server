package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.common.exception.EntityNotFoundException;
import com.oldhandgo.common.utils.ValidationUtil;
import com.oldhandgo.system.modules.system.domain.Permission;
import com.oldhandgo.system.modules.system.domain.User;
import com.oldhandgo.system.modules.system.repository.UserRepository;
import com.oldhandgo.system.modules.system.service.UserService;
import com.oldhandgo.system.modules.system.service.dto.UserDTO;
import com.oldhandgo.system.modules.system.service.mapper.UserMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dormir
 */
@Service
@CacheConfig(cacheNames = "users")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Cacheable(key = "#id")
    public UserDTO findById(long id) {
        return null;
    }

    @Override
    @Cacheable(key = "#email")
    public UserDTO findByEmail(String email) {
        User user;
        if (ValidationUtil.isEmail(email)) {
            user = userRepository.findByEmail(email);
        } else {
            throw new EntityNotFoundException(User.class, "email", email);
        }
        return userMapper.userToUserDto(user);
    }
}
