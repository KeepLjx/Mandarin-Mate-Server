package com.mandrain_mate;

import com.mandrain_mate.mapper.UserMapper;
import com.mandrain_mate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MandrainMateApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void testSelectList() {
        userService.list(null).forEach(System.out::println);
        System.out.println(userService.count());
    }
}