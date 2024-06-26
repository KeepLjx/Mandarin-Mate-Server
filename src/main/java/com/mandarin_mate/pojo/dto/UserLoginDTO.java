package com.mandarin_mate.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.pojo.dto
 * @Author: kc
 * @CreateTime: 2023-10-28  13:39
 * @Description: 用户登录数据模型
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private Long userId;

    private String password;

    private String phone;

    private String userMail;

    //微信登录要用到code
    private String code;
}
