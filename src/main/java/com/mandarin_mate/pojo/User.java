package com.mandarin_mate.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName user
 */
@TableName(value ="user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    //注册时间
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;


}