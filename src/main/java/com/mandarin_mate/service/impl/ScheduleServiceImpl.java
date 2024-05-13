package com.mandarin_mate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandarin_mate.constant.MessageConstant;
import com.mandarin_mate.mapper.UserMapper;
import com.mandarin_mate.pojo.Schedule;
import com.mandarin_mate.pojo.dto.UserDTO;
import com.mandarin_mate.service.ScheduleService;
import com.mandarin_mate.mapper.ScheduleMapper;
import com.mandarin_mate.utils.JwtHelper;
import com.mandarin_mate.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lenovo
 * @description 针对表【schedule(用户进度表)】的数据库操作Service实现
 * @createDate 2023-10-27 23:02:02
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
        implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;


    /**
     * 创建用户学习进度表业务实现
     *
     * @param bookId
     * @param token
     * @return
     */
    @Override
    public Result buildSchedule(Long bookId, String token) {
        Long userId = jwtHelper.getUserId(token);
        Schedule schedules = scheduleMapper.selectScheduleByUserIdAndBookId(bookId, userId);
        userMapper.updateInfo(new UserDTO(userId, bookId));
        if (!(schedules == null)) {
            return Result.ok(MessageConstant.PROGRESS_CREATED);
        }
        Schedule schedule = new Schedule();
        //修改用户信息中的词书信息
        schedule.setBookId(bookId);
        schedule.setUserId(userId);
        schedule.setCompleted("");
        schedule.setIsDelete(0);
        scheduleMapper.insert(schedule);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("bookId", schedule.getBookId());
        map.put("userSchedule", schedule.getCompleted());
        return Result.ok(map);
    }

    /**
     * 获取用户进度业务实现
     *
     * @param token
     * @return
     */
    @Override
    public Result getSchedule(Long bookId, String token) {
        Long userId = jwtHelper.getUserId(token);
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId, userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getBookId, bookId);
        scheduleLambdaQueryWrapper.eq(Schedule::getIsDelete, 0L);
        Schedule schedule = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        if (schedule == null) {
            return Result.ok("schedule is null");
        }
        String[] completed = schedule.getCompleted().split(",");
        HashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("bookId", schedule.getBookId());
        map.put("schedule", completed.length);
        map.put("completed",schedule.getCompleted());
        map.put("review", schedule.getReview());
        return Result.ok(map);
    }

    /**
     * 用户切换词书业务实现
     *
     * @param token
     * @param nowBookId
     * @param switchBookId
     * @return
     */
    @Override
    public Result switchBookSchedule(String token, Long nowBookId, Long switchBookId) {
        Long userId = jwtHelper.getUserId(token);
        //查询数据库中是否以前就有
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId, userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getBookId, switchBookId);
        Schedule scheduleTemp = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        //关闭当前词书进度
        LambdaUpdateWrapper<Schedule> scheduleLambdaUpdateWrapperClose = new LambdaUpdateWrapper<>();
        scheduleLambdaUpdateWrapperClose.eq(Schedule::getBookId, nowBookId);
        Schedule schedule = new Schedule();
        schedule.setIsDelete(1);
        scheduleMapper.update(schedule, scheduleLambdaUpdateWrapperClose);
        //修改用户信息中的词书信息
        userMapper.updateInfo(new UserDTO(userId, switchBookId));
        System.out.println(scheduleTemp);
        //如果有则恢复学习进度
        if (scheduleTemp != null) {
            LambdaUpdateWrapper<Schedule> scheduleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            scheduleLambdaUpdateWrapper.eq(Schedule::getUserId, userId);
            scheduleLambdaUpdateWrapper.eq(Schedule::getBookId, switchBookId);
            scheduleTemp.setIsDelete(0);
            scheduleMapper.update(scheduleTemp, scheduleLambdaUpdateWrapper);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("bookId", scheduleTemp.getBookId());
            //获取词书进度 completed 1,2,3,4(wordsId)
            String[] completed = scheduleTemp.getCompleted().split(",");
            map.put("userSchedule", completed.length);
            return Result.ok(map);
        }
        //如果没有，则创建新的进度
        return buildSchedule(switchBookId, token);
    }

    /**
     * 重置用户学习进度
     *
     * @param token
     * @param bookId
     * @return
     */
    @Override
    public Result resetSchedule(String token, Long bookId) {
        //通过token获取用户id
        Long userId = jwtHelper.getUserId(token);
        //添加修改数据条件
        LambdaUpdateWrapper<Schedule> scheduleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        scheduleLambdaUpdateWrapper.eq(Schedule::getUserId, userId);
        scheduleLambdaUpdateWrapper.eq(Schedule::getBookId, bookId);
        Schedule schedule = new Schedule();
        schedule.setCompleted("");
        //将用户学习进度修改为0
        scheduleMapper.update(schedule, scheduleLambdaUpdateWrapper);
        //添加查询条件
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId, userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getBookId, bookId);
        //查询用户重置之后的学习进度相关信息
        Schedule schedule1 = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("schedule", schedule1);
        return Result.ok(map);
    }

    /**
     * 更新用户学习进度
     *
     * @param token
     * @param bookId
     * @return
     */
    @Override
    public Result addSchedule(String token, Long bookId, Long wordsId) {
        Long userId = jwtHelper.getUserId(token);
        Schedule schedule = scheduleMapper.selectScheduleByUserIdAndBookId(bookId, userId);
        if (schedule.getCompleted() == null) {
            scheduleMapper.updateCompletedByBookIdAndUserId(String.valueOf(wordsId), bookId, userId);
            scheduleMapper.updateReviewByBookIdAndUserId(String.valueOf(wordsId), bookId, userId);
            return Result.ok("Progress has been updated");
        }
        String[] completed = schedule.getCompleted().split(",");
        for (int i = 0; i < completed.length; i++) {
            if (completed[i].equals(String.valueOf(wordsId))) {
                return Result.ok("The user has already learned the word");
            }
        }
        StringBuffer sb = new StringBuffer(schedule.getCompleted());
        sb.append("," + wordsId);
        scheduleMapper.updateCompletedByBookIdAndUserId(sb.toString(), bookId, userId);
        scheduleMapper.updateReviewByBookIdAndUserId(sb.toString(), bookId, userId);
        return Result.ok("Progress has been updated");
    }

    /**
     * 用户删除复习模块中已经熟练的单词
     *
     * @param token
     * @param bookId
     * @param wordsId
     * @return
     */
    @Override
    public Result deleteSchedule(String token, Long bookId, Long wordsId) {
        Long userId = jwtHelper.getUserId(token);
        String wordStr = String.valueOf(wordsId);
        Schedule schedule = scheduleMapper.selectScheduleByUserIdAndBookId(userId, bookId);
        String review = schedule.getReview();
        if (!review.contains(",")) {
            scheduleMapper.updateReviewByBookIdAndUserId("", bookId, userId);
        } else {
            int exist = review.indexOf(wordStr);
            if (exist == 0) {
                review = review.substring(0, exist) + review.substring(exist + wordStr.length() + 1);
                scheduleMapper.updateReviewByBookIdAndUserId(review, bookId, userId);
            } else {
                review = review.substring(0, exist - 1) + review.substring(exist + wordStr.length());
                scheduleMapper.updateReviewByBookIdAndUserId(review, bookId, userId);
            }
        }
        return Result.ok("Has deleted");
    }
}




