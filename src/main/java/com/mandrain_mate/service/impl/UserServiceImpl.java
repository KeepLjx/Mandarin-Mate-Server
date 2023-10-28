package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.User;
import com.mandrain_mate.service.UserService;
import com.mandrain_mate.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2023-10-27 23:02:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




