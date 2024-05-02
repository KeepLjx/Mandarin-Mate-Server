package com.mandarin_mate.pojo.dto;

import lombok.Data;

/**
 * @description: 用户注册表单
 * @author：kc
 * @date: 2024/3/17
 */
@Data
public class UserRegisterFormDTO {
    private String nickName;

    private String password;

    private String UserMail;

    private String registerCode;
}
