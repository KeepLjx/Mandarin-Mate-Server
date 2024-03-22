package com.mandarin_mate.config;

import com.mandarin_mate.interceptors.LoginProtectedInterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.config
 * @Author: kc
 * @CreateTime: 2023-10-28  11:39
 * @Description:
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private LoginProtectedInterceptor loginProtectedInterceptor;
    @Value("${prop.upload-folder}")
    private String UPLOAD_PATH;

    //检查token是否合法
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectedInterceptor)
                .addPathPatterns("/user/**","/book/**")
                .excludePathPatterns("/user/login","/user/register","/book","/user/weChatLogin","/user/userMail");
    }

    /**
     * 图片资源读取服务
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_PATH);
    }
}
