package com.mandarin_mate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandarin_mate.pojo.Schedule;
import com.mandarin_mate.service.ScheduleService;
import com.mandarin_mate.mapper.ScheduleMapper;
import com.mandarin_mate.utils.JwtHelper;
import com.mandarin_mate.utils.Result;
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
        Schedule schedule = new Schedule();
        schedule.setBookId(bookId);
        schedule.setUserId(userId);
        schedule.setCompleted(0L);
        schedule.setIsDelete(0);
        scheduleMapper.insert(schedule);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("bookId",schedule.getBookId());
        map.put("userSchedule",schedule.getCompleted());
        return Result.ok(map);
    }

    /**
     * 获取用户进度业务实现
     * @param token
     * @return
     */
    @Override
    public Result getSchedule(String token) {
        Long userId = jwtHelper.getUserId(token);
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId,userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getIsDelete,0L);
        Schedule schedule = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("bookId",schedule.getBookId());
        map.put("schedule",schedule.getCompleted());
        return Result.ok(map);
    }

    /**
     * 用户切换词书业务实现
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
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId,userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getBookId,switchBookId);
        Schedule scheduleTemp = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        //关闭当前词书进度
        LambdaUpdateWrapper<Schedule> scheduleLambdaUpdateWrapperClose = new LambdaUpdateWrapper<>();
        scheduleLambdaUpdateWrapperClose.eq(Schedule::getBookId,nowBookId);
        Schedule schedule = new Schedule();
        schedule.setIsDelete(1);
        scheduleMapper.update(schedule,scheduleLambdaUpdateWrapperClose);
        System.out.println(scheduleTemp);
        //如果有则恢复学习进度
        if(scheduleTemp != null){
            LambdaUpdateWrapper<Schedule> scheduleLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            scheduleLambdaUpdateWrapper.eq(Schedule::getUserId,userId);
            scheduleLambdaUpdateWrapper.eq(Schedule::getBookId,switchBookId);
            scheduleTemp.setIsDelete(0);
            scheduleMapper.update(scheduleTemp,scheduleLambdaUpdateWrapper);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("bookId",scheduleTemp.getBookId());
            map.put("userSchedule",scheduleTemp.getCompleted());
            return Result.ok(map);
        }
        //如果没有，则创建新的进度
        return buildSchedule(switchBookId,token);
    }

    /**
     * 重置用户学习进度
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
        scheduleLambdaUpdateWrapper.eq(Schedule::getUserId,userId);
        scheduleLambdaUpdateWrapper.eq(Schedule::getBookId,bookId);
        Schedule schedule = new Schedule();
        schedule.setCompleted(0L);
        //将用户学习进度修改为0
        scheduleMapper.update(schedule,scheduleLambdaUpdateWrapper);
        //添加查询条件
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getUserId,userId);
        scheduleLambdaQueryWrapper.eq(Schedule::getBookId,bookId);
        //查询用户重置之后的学习进度相关信息
        Schedule schedule1 = scheduleMapper.selectOne(scheduleLambdaQueryWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("schedule",schedule1);
        return Result.ok(map);
    }
}




