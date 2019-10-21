package com.oldhandgo;

import com.oldhandgo.modules.system.domain.User;
import com.oldhandgo.modules.system.repository.UserRepository;
import com.oldhandgo.modules.system.service.mapper.UserMapper;
import com.oldhandgo.utils.ValidationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
        Boolean isEnabled = true;
        User user = null;
        if (ValidationUtils.isEmail(email)) {
            user = userRepository.findByEmail(email);
        }
        if (user != null) {
            System.out.println(userMapper.toDto(user));
        } else {
            System.out.println("空");
        }
    }

    @Test
    public void findById() {
        Long id = 1L;
        Optional<User> user = userRepository.findById(id);
        ValidationUtils.isNull(user, "User", "id", id);
        System.out.println(user.get());
    }
}
