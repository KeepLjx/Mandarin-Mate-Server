package com.mandrain_mate.service.impl;

import com.mandrain_mate.utils.Constants;
import com.mandrain_mate.utils.Result;
import com.mandrain_mate.utils.ResultCodeEnum;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：kc
 * @date: 2024/3/17
 */
@Service
@Slf4j
public class MailService {
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${spring.mail.username}")
    private String sendMailer;
    public Result sendTextMailMessage(String to,String subject,String text){
        String msg = Constants.VerificationInfo.setText(text);
        String msgTo = "<"+to+">";
        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1个
            mimeMessageHelper.setTo(msgTo);
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(msg);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            //验证码存入redis
            stringRedisTemplate.opsForValue().set(Constants.RedisConstants.LOGIN_CODE_KEY+to,text, Constants.RedisConstants.LOGIN_CODE_TTL , TimeUnit.MINUTES);
            log.info("发送邮件成功："+sendMailer+"->"+to);
            return Result.ok("发送邮件成功");
        } catch (MessagingException e) {
            log.info("发送邮件失败："+e.getMessage());
            return Result.build(null, ResultCodeEnum.EMail_ERROR);
        }
    }

}
