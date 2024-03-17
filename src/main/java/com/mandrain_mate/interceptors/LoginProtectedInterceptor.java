package com.mandrain_mate.interceptors;

import com.mandrain_mate.utils.JwtHelper;
import com.mandrain_mate.utils.Result;
import com.mandrain_mate.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * @BelongsProject: Spring-boot-demo
 * @BelongsPackage: com.atguigu.interceptors
 * @Author: kc
 * @CreateTime: 2023-10-22  23:27
 * @Description: 验证用户token是否合格
 * @Version: 1.0
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        String token = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(token);
        if(!expiration){
            return true;
        }
        Result<Object> build = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(build);
        response.getWriter().print(s);
        return false;
    }

}
