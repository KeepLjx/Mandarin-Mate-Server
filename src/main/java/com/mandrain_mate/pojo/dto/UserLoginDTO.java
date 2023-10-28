package com.mandrain_mate.pojo.dto;

import lombok.Data;

/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.pojo.dto
 * @Author: kc
 * @CreateTime: 2023-10-28  13:39
 * @Description: 用户登录数据模型
 * @Version: 1.0
 */
@Data
public class UserLoginDTO {
    private Long userId;


    private String password;


    private String phone;

    private String userMail;
}
