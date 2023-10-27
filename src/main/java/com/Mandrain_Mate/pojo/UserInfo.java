package com.Mandrain_Mate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author:CookieBoy
 * @Description
 * @create 2023/10/27
 * @version:1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    // 用户ID
    private BigInteger userId;
    // 用户昵称
    private String nickName;
    // 用户密码
    private String password;
    // 是否为会员 1:是  0:否
    private int isVIP;
    // 头像路径
    private String avatarPath;
    // 学习等级
    private int learning_level;
    // 用户电话
    private String phone;
    // 用户邮箱
    private String userMail;
    // 微信小程序用户唯一id
    private String open_id;
}
