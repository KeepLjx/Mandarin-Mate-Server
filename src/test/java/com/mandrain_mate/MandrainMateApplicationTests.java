package com.mandrain_mate;

import com.mandrain_mate.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MandrainMateApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectList() {
        userMapper.selectList(null).forEach(System.out::println);
    }
}
