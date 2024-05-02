package com.mandarin_mate.mapper;

import com.mandarin_mate.pojo.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author lenovo
* @description 针对表【schedule(用户进度表)】的数据库操作Mapper
* @createDate 2023-10-27 23:02:02
* @Entity com.mandrain_mate.pojo.Schedule
*/
@Repository
public interface ScheduleMapper extends BaseMapper<Schedule> {


    List<Schedule> selectScheduleByUserIdAndBookId(Long bookId, Long userId);
}




