package com.oldhandgo.system.modules.system.service.impl;

import com.oldhandgo.common.exception.EntityNotFoundException;
import com.oldhandgo.common.utils.ValidationUtil;
import com.oldhandgo.system.SystemApplication;
import com.oldhandgo.system.modules.system.domain.User;
import com.oldhandgo.system.modules.system.repository.UserRepository;
import com.oldhandgo.system.modules.system.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SystemApplication.class)
public class UserServiceImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Test
    public void findByEmail() {
        String email = "1018729292@qq.com";
        User user;
        if (ValidationUtil.isEmail(email)) {
            user = userRepository.findByEmail(email);
        } else {
            throw new EntityNotFoundException(User.class, "email", email);
        }
        System.out.println(userMapper.userToUserDto(user));
    }
}