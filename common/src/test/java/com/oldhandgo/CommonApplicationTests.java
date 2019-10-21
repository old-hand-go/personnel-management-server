package com.oldhandgo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void encryptPassword() {
        String password = "123456";
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
