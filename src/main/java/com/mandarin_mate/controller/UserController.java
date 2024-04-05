package com.mandarin_mate.controller;

import com.mandarin_mate.pojo.User;
import com.mandarin_mate.pojo.dto.UserLoginDTO;
import com.mandarin_mate.pojo.vo.UserLoginVO;
import com.mandarin_mate.service.impl.UserServiceImpl;
import com.mandarin_mate.utils.JwtHelper;
import com.mandarin_mate.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.controller
 * @Author: kc
 * @CreateTime: 2023-10-28  11:11
 * @Description: 用户模块相关接口实现
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserServiceImpl userService;
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @Autowired
    private JwtHelper jwtHelper;


    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody User user){
        Result result = userService.register(user);
        return result ;
    }


    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO){
        Result result  = userService.login(userLoginDTO);
        return result;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }
    /**
     * 用户头像自定义上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    //todo 加上api注解
    @Operation(tags = "什么功能")
    public Result upload(@RequestBody MultipartFile file, HttpServletRequest request,@RequestHeader String token) {
        if (file == null) {
            return Result.build(null,0,"头像上传文件为空");
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            return Result.build(null,0, "文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return Result.build(null,0, "请选择jpg,jpeg,gif,png格式的图片");
        }
        String savePath = UPLOAD_FOLDER;
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(null,0, "保存文件异常");
        }
        userService.saveUserImg(filename,token);
        HashMap map = new HashMap();
        map.put("fileName",filename);
        //返回文件名称
        return Result.ok(map);
    }

    @PostMapping("/weChatLogin")
    @Operation(tags = "微信登录")
    public Result weChatLogin(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信登录: {}", userLoginDTO.getCode());
        //微信登录
        User user = userService.wxLogin(userLoginDTO);

        //为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        String token = jwtHelper.createToken(user.getUserId());
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .openid(user.getOpenId())
                .token(token)
                .createTime(user.getCreateTime())
                .build();
        return Result.ok(userLoginVO);
    }
}
