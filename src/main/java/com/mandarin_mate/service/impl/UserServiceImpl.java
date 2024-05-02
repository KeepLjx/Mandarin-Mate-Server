package com.mandarin_mate.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandarin_mate.constant.MessageConstant;
import com.mandarin_mate.exception.LoginFailedException;
import com.mandarin_mate.pojo.User;
import com.mandarin_mate.pojo.dto.UserDTO;
import com.mandarin_mate.pojo.dto.UserLoginDTO;
import com.mandarin_mate.pojo.dto.UserRegisterFormDTO;
import com.mandarin_mate.properties.WeChatProperties;
import com.mandarin_mate.service.UserService;
import com.mandarin_mate.mapper.UserMapper;
import com.mandarin_mate.utils.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private WeChatProperties weChatProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 用户注册业务实现
     * @param registerUser
     * @return
     */
    @Override
    public Result register(UserRegisterFormDTO registerUser) {
        //验证码是否正确
        String code = registerUser.getRegisterCode();
        String redisCode  = stringRedisTemplate.opsForValue().get(Constans.RedisConstants.LOGIN_CODE_KEY + registerUser.getUserMail());
        if (redisCode == null || !redisCode.contains(code)) {
            return Result.build(null, ResultCodeEnum.REGISTER_CODE_EMail_ERROR);
        }
        User user = new User();
        user.setNickName(registerUser.getNickName());
        user.setPassword(registerUser.getPassword());
        user.setUserMail(registerUser.getUserMail());
        user.setAvatarPath("defult");  // 设置默认头像
        user.setIsvip(0);  // 默认非vip用户
        user.setCreateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        if( insert != 0 ){
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

    /**
     * 获取用户信息业务实现
     * @param token
     * @return
     */
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

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());

        //判断openid是否为空，如果为空表示登录失败，抛出业务异常
        if(openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //判断当前用户是否为新用户
        User user = userMapper.selectByOpenId(openid);

        //如果是新用户，自动完成注册

        if(user == null) {
            user = User.builder()
                    .openId(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        //返回这个用户对象
        return user;
    }

    /**
     * 更新用户信息
     * @param userDTO
     */
    @Override
    public void updateInfo(UserDTO userDTO) {
        userMapper.updateInfo(userDTO);
    }

    /**
     * 调用微信接口服务，获取微信用户的openid
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        //调用微信接口服务，获得当前微信用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }

}




