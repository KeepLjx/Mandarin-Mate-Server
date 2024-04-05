package com.mandarin_mate.controller;

import com.mandarin_mate.constant.MessageConstant;
import com.mandarin_mate.service.UserService;
import com.mandarin_mate.utils.AliOssUtil;
import com.mandarin_mate.utils.JwtHelper;
import com.mandarin_mate.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/4/4
 * @version:1.0
 */

@Slf4j
@RestController
@RequestMapping("/common")
@ApiResponse(description = "通用接口")
public class commonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 通用文件上传
     * @param file
     * @param path
     * @return
     */
    @PostMapping("/upload")
    @Operation(description = "文件上传")
    public Result<String> upload(@RequestParam MultipartFile file, @RequestParam String path) {
        log.info("文件上传：{}",file);
        try {
            //原始文件名
            //String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            //String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(),path);
            return Result.ok(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return Result.build(null,0, MessageConstant.UPLOAD_FAILED);
    }
}
