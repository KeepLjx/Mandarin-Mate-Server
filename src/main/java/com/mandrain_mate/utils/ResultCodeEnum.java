package com.mandrain_mate.utils;

/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    REGISTER_ERROR(506,"服务器注册失败"),
    USER_NULL(2,"用户为空，请注册"),
    USER_LOGIN_NULL(3,"传入参数为空,请输入"),
    USERNAME_USED(505,"userNameUsed");

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}

