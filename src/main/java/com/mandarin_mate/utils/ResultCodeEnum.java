package com.mandarin_mate.utils;

/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    REGISTER_ERROR(506,"Registration failure."),
    USER_NULL(2,"Please register."),
    USER_LOGIN_NULL(3,"Please Input"),
    USERNAME_USED(505,"UserName Used"),
    EMail_ERROR(4,"Failed,please try again."),
    Email_exist(6,"Has already registered."),
    REGISTER_CODE_EMail_ERROR(5, "Code error");

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
