package com.mandarin_mate.service;

import com.mandarin_mate.utils.Result;

/**
 * @description:
 * @author：kc
 * @date: 2024/3/22
 */
public interface MailService {

    /**
     * 发送文本邮件
     * @param to 发送对象
     * @param subject 主题
     * @param text 验证码
     * @return
     */

    Result sendTextMailMessage (String to, String subject, String text);
}
