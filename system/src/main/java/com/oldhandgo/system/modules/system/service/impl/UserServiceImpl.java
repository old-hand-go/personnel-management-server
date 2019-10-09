//package com.oldhandgo.system.modules.system.service.impl;
//
//import com.oldhandgo.common.utils.ValidationUtil;
//import com.oldhandgo.system.modules.system.domain.User;
//import com.oldhandgo.system.modules.system.repository.UserRepository;
//import com.oldhandgo.system.modules.system.service.UserService;
//import com.oldhandgo.system.modules.system.service.dto.UserDTO;
//import com.oldhandgo.system.modules.system.service.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author dormir
// */
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public UserDTO findById(long id) {
//        return null;
//    }
//
//    @Override
//    public UserDTO findByUserName(String userName) throws Exception {
//        User user = null;
//        if (ValidationUtil.isEmail(userName)) {
//            user = userRepository.findByEmail(userName);
//        } else {
//            throw new Exception();
//        }
//        return userMapper.userToUserDto(user);
//
//    }
//}
