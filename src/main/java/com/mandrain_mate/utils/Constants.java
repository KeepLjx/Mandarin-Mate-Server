package com.mandrain_mate.utils;

/**
 * @description:
 * @author：kc
 * @date: 2024/3/17
 */
public class Constants {
    public class Verification{
        public static final String TOPIC = "用户注册验证码";
    }
    public class VerificationInfo{
        public static final String megPre = "您的验证码为:";
        public static final String megPost = ",请在5分钟内完成验证，否则验证码将失效。";

        public static String setText(String code){
            return megPre + code + megPost;
        }
    }
    public class RedisConstants{
        public static final String LOGIN_CODE_KEY = "register:code:";
        public static final Long LOGIN_CODE_TTL = 4L;
    }
}
