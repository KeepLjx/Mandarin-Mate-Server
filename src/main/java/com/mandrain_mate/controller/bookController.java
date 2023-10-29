package com.mandrain_mate.controller;

import com.mandrain_mate.service.BookService;
import com.mandrain_mate.service.impl.BookServiceImpl;
import com.mandrain_mate.service.impl.ScheduleServiceImpl;
import com.mandrain_mate.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.controller
 * @Author: kc
 * @CreateTime: 2023-10-28  20:16
 * @Description: 关于汉语词书的api开发
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("book")

public class bookController {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private ScheduleServiceImpl scheduleService;

    @GetMapping
    public Result getBookInfo(){
        Result result = bookService.getBookInfo();
        return result;
    }

    /**
     * 用户选择词书
     * @param bookId
     * @param token
     * @return
     */
    @GetMapping("schedule")
    public Result buildSchedule(@RequestParam Long bookId ,
                                @RequestHeader String token){
        Result result = scheduleService.buildSchedule(bookId,token);
        return result;
    }

    /**
     * 获取用户该词书学习进度
     * @param token
     * @return
     */
    @GetMapping("getSchedule")
    public Result getSchedule(@RequestHeader String token){
        Result result = scheduleService.getSchedule(token);
        return result;
    }

}
