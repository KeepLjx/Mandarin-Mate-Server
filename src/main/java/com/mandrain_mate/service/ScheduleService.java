package com.mandrain_mate.service;

import com.mandrain_mate.pojo.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mandrain_mate.utils.Result;

/**
* @author lenovo
* @description 针对表【schedule(用户进度表)】的数据库操作Service
* @createDate 2023-10-27 23:02:02
*/
public interface ScheduleService extends IService<Schedule> {

    /**
     * 创建用户进度表业务
     * @param bookId
     * @param token
     * @return
     */
    Result buildSchedule(Long bookId, String token);

    /**
     * 获取用户该词书进度
     * @param token
     * @return
     */
    Result getSchedule(String token);


    /**
     * 用户更改词书
     * @param token
     * @param nowBookId
     * @param switchBookId
     * @return
     */
    Result switchBookSchedule(String token, Long nowBookId, Long switchBookId);

    /**
     * 重置用户学习进度
     * @param token
     * @param bookId
     * @return
     */
    Result resetSchedule(String token, Long bookId);
}
