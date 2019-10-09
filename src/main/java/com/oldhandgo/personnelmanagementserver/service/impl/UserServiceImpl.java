package com.oldhandgo.personnelmanagementserver.service.impl;

import com.oldhandgo.personnelmanagementserver.domain.User;
import com.oldhandgo.personnelmanagementserver.repository.UserRepository;
import com.oldhandgo.personnelmanagementserver.service.UserService;
import com.oldhandgo.personnelmanagementserver.service.dto.UserDTO;
import com.oldhandgo.personnelmanagementserver.service.mapper.UserMapper;
import com.oldhandgo.personnelmanagementserver.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dormir
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findById(long id) {
        return null;
    }

    @Override
    public UserDTO findByUserName(String userName) throws Exception {
        User user = null;
        if (ValidationUtil.isEmail(userName)) {
            user = userRepository.findByEmail(userName);
        } else {
            throw new Exception();
        }
        return userMapper.userToUserDto(user);

    }
}
