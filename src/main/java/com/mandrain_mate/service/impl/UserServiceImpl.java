package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.User;
import com.mandrain_mate.service.UserService;
import com.mandrain_mate.mapper.UserMapper;
import com.mandrain_mate.utils.Result;
import com.mandrain_mate.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2023-10-27 23:02:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册业务实现
     * @param user
     * @return
     */
    @Override
    public Result register(User user) {
        user.setAvatarPath("kkkkk");
        user.setIsvip(0);
        int insert = userMapper.insert(user);
        if(insert !=0){
            return Result.ok(null);
        }
        return Result.build(null, ResultCodeEnum.REGISTER_ERROR);
    }
}




