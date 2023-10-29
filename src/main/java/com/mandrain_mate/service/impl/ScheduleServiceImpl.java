package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.Schedule;
import com.mandrain_mate.pojo.User;
import com.mandrain_mate.service.ScheduleService;
import com.mandrain_mate.mapper.ScheduleMapper;
import com.mandrain_mate.utils.JwtHelper;
import com.mandrain_mate.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
* @author lenovo
* @description 针对表【schedule(用户进度表)】的数据库操作Service实现
* @createDate 2023-10-27 23:02:02
*/
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule>
    implements ScheduleService{

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private JwtHelper jwtHelper;
    /**
     * 创建用户学习进度表业务实现
     * @param bookId
     * @param token
     * @return
     */
    @Override
    public Result buildSchedule(Long bookId, String token) {
        Long userId = jwtHelper.getUserId(token);
//        //查询数据库中是否以前就有
//        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        scheduleLambdaQueryWrapper.eq(Schedule::getUserId,userId);
//        scheduleLambdaQueryWrapper.eq(Schedule::getBookId,bookId);
//        Schedule scheduleTemp = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
//        //如果有则恢复学习进度
//        if(scheduleTemp != null){
//            LambdaUpdateWrapper<Schedule> scheduleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
//            scheduleLambdaUpdateWrapper.eq(Schedule::getUserId,userId);
//            scheduleLambdaUpdateWrapper.eq(Schedule::getBookId,bookId);
//            scheduleTemp.setIsDelete(0);
//            scheduleMapper.update(scheduleTemp,scheduleLambdaUpdateWrapper);
//            HashMap<Object, Object> map = new HashMap<>();
//            map.put("userSchedule",scheduleTemp.getCompleted());
//            return Result.ok(map);
//        }
        Schedule schedule = new Schedule();
        schedule.setBookId(bookId);
        schedule.setUserId(userId);
        schedule.setCompleted(0L);
        schedule.setIsDelete(0);
        scheduleMapper.insert(schedule);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("userSchedule",schedule.getCompleted());
        return Result.ok(map);
    }

    @Override
    public Result getSchedule(String token) {
        Long userId = jwtHelper.getUserId(token);
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId,userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getIsDelete,0L);
        Schedule schedule = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("schedule",schedule.getCompleted());
        return Result.ok(map);
    }
}




