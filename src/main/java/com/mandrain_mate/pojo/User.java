package com.mandrain_mate.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;


import lombok.Data;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    private Long userId;

    private String nickName;

    private String password;

    private Integer isvip;

    private String avatarPath;

    private Integer learningLevel;

    private String phone;

    private String userMail;

    private String openId;

    private static final long serialVersionUID = 1L;
}