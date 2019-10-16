package com.oldhandgo.system;

import com.oldhandgo.common.utils.ValidationUtils;
import com.oldhandgo.system.modules.system.domain.User;
import com.oldhandgo.system.modules.system.repository.UserRepository;
import com.oldhandgo.system.modules.system.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findByEmail() {
        String email = "1018729292@qq.com";
        Byte isEnabled = 1;
        User user = null;
        if (ValidationUtils.isEmail(email)) {
            user = userRepository.findByEmailAndIsEnabled(email, isEnabled).orElse(null);
        }
        if (user != null) {
            System.out.println(userMapper.userToUserDto(user));
        } else {
            System.out.println("空");
        }
    }
}
