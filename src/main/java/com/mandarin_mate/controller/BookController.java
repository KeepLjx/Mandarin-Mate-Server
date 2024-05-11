package com.mandarin_mate.controller;

import com.mandarin_mate.service.impl.BookServiceImpl;
import com.mandarin_mate.service.impl.ScheduleServiceImpl;
import com.mandarin_mate.service.impl.WordsInfoServiceImpl;
import com.mandarin_mate.utils.Result;
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

public class BookController {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private ScheduleServiceImpl scheduleService;
    @Autowired
    private WordsInfoServiceImpl wordsInfoService;

    /**
     * 获取词书信息
     * @return
     */
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
    public Result getSchedule(@RequestParam Long bookId ,
                              @RequestHeader String token){
        Result result = scheduleService.getSchedule(bookId, token);
        return result;
    }

    /**
     * 用户选择其他词书
     * @param token
     * @param nowBookId
     * @param switchBookId
     * @return
     */
    @GetMapping("switchBookSchedule")
    public Result switchBookSchedule(@RequestHeader String token,
                                     @RequestParam Long nowBookId,
                                     @RequestParam Long switchBookId){
        Result result = scheduleService.switchBookSchedule(token,nowBookId,switchBookId);
        return result;
    }
    /**
     * 用户重置词书学习进度
     * @param token
     * @param bookId
     * @return
     */
    @GetMapping("resetSchedule")
    public Result resetSchedule(@RequestHeader String token,
                                @RequestParam Long bookId){
        Result result = scheduleService.resetSchedule(token,bookId);
        return result;
    }

    /**
     * 更新用户学习进度
     * @param token
     * @param bookId
     * @return
     */
    @PostMapping("addSchedule")
    public Result addSchedule(@RequestHeader String token,
                              @RequestParam Long bookId,
                              @RequestParam Long wordsId) {
        Result result = scheduleService.addSchedule(token, bookId, wordsId);
        return result;
    }
    /**
     * 获取学习词书的单词信息
     * @param bookId
     * @return
     */
    @GetMapping("getBookWordsInfo")
    public Result getBookInfo(
                              @RequestParam Long bookId){
        Result result = wordsInfoService.getBookInfo(bookId);
        return  result;
    }


    /**
     * 删除复习模块中已经熟练的单词
     * @param token
     * @param bookId
     * @param wordsId
     * @return
     */
    @PostMapping("deleteSchedule")
    public Result deleteSchedule(@RequestHeader String token,
                                 @RequestParam Long bookId,
                                 @RequestParam Long wordsId) {
        Result result = scheduleService.deleteSchedule(token, bookId, wordsId);
        return result;
    }
}
