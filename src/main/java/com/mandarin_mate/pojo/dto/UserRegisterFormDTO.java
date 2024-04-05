package com.mandarin_mate.pojo.dto;

/**
 * @description: 用户注册表单
 * @author：kc
 * @date: 2024/3/17
 */
public class UserRegisterFormDTO {
    private String nickName;

    private String password;

    private String UserMail;

    private String registerCode;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }
}
