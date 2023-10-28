package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.User;
import com.mandrain_mate.pojo.dto.UserLoginDTO;
import com.mandrain_mate.service.UserService;
import com.mandrain_mate.mapper.UserMapper;
import com.mandrain_mate.utils.JwtHelper;
import com.mandrain_mate.utils.Result;
import com.mandrain_mate.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    @Autowired
    private JwtHelper jwtHelper;

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

    /**
     * 用户登录业务实现
     * @param userLoginDTO
     * @return
     */
    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //判断传入参数有哪一个属性
        if(userLoginDTO.getUserId() != null && userLoginDTO.getUserId() != 0){
            userLambdaQueryWrapper.eq(User::getUserId,userLoginDTO.getUserId());
        }else if(userLoginDTO.getUserMail() != null && !userLoginDTO.getUserMail().equals("")){
            userLambdaQueryWrapper.eq(User::getUserMail,userLoginDTO.getUserMail());
        }else if(userLoginDTO.getPhone() != null && !userLoginDTO.getPhone().equals("")){
            userLambdaQueryWrapper.eq(User::getPhone,userLoginDTO.getPhone());
        }else{
            return Result.build(null,ResultCodeEnum.USER_LOGIN_NULL);
        }
        //根据相关属性加入条件进行查询
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        //用户为空返回错误信息
        if(user == null){
            return Result.build(null,ResultCodeEnum.USER_NULL);
        }
        //成功返回token
        if(user.getPassword().equals(userLoginDTO.getPassword())){
            String token = jwtHelper.createToken(user.getUserId());
            HashMap<Object, Object> map = new HashMap<>();
            user.setPassword("");
            map.put("token",token);
            map.put("userInfo",user);
            return Result.ok(map);
        }else{
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
    }

    /**
     * 更新用户存储头像路径业务实现
     * @param filename
     * @param token
     */
    @Override
    public void saveUserImg(String filename, String token) {
        User user = new User();
        user.setAvatarPath(filename);
        //利用jwt解析用户id
        Long userId = jwtHelper.getUserId(token);
        //将文件路径加入用户数据库中
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getUserId,userId);
        userMapper.update(user,userLambdaUpdateWrapper);
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.eq(User::getUserId,userId);
//        User user = userMapper.selectOne(userLambdaQueryWrapper);
//        user.setAvatarPath(filename);
//        userMapper.update(user, userLambdaQueryWrapper);
    }

    @Override
    public Result getUserInfo(String token) {
        //jwt解析id
        Long userId = jwtHelper.getUserId(token);
        //查询用户并返回
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserId,userId);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        user.setPassword("");
        HashMap map = new HashMap();
        map.put("userInfo",user);
        return Result.ok(map);
    }

}




