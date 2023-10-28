package com.mandrain_mate.controller;

import com.mandrain_mate.pojo.User;
import com.mandrain_mate.service.impl.UserServiceImpl;
import com.mandrain_mate.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.controller
 * @Author: kc
 * @CreateTime: 2023-10-28  11:11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("user")
@Tag(name = "用户模块")
public class userController {
    @Autowired
    private UserServiceImpl userService;


    /**
     * 用户注册
     * @param user
     * @return
     */
    @Operation(summary = "用户注册")
    @PostMapping("register")
    public Result register(@RequestBody User user){
        System.out.println(user);
        Result result = userService.register(user);
        return result ;
    }
}
