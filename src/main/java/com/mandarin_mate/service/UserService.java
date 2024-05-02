package com.mandarin_mate.service;

import com.mandarin_mate.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mandarin_mate.pojo.dto.*;
import com.mandarin_mate.utils.Result;

/**
* @author lenovo
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2023-10-27 23:02:06
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册业务
     * @param user
     * @return
     */
    Result register(UserRegisterFormDTO user);

    /**
     * 用户登录业务
     * @param userLoginDTO
     * @return
     */
    Result login(UserLoginDTO userLoginDTO);

    /**
     * 更新用户存储头像路径业务
     *
     * @param filename
     * @param token
     */
    void saveUserImg(String filename, String token);

    /**
     * 获取用户信息业务
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);

    /**
     * 更新用户信息
     * @param userDTO
     */
    void updateInfo(UserDTO userDTO);

    /**
     * 用户绑定邮箱
     * @param bindMailDTO
     */
    String bindMail(BindMailDTO bindMailDTO);

    /**
     * 用户绑定微信
     * @param bindWeChatDTO
     */
    String bindWeChat(BindWeChatDTO bindWeChatDTO);
}
