package com.mandarin_mate.mapper;
import org.apache.ibatis.annotations.Param;

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


    Schedule selectScheduleByUserIdAndBookId(Long bookId, Long userId);

    int updateCompletedByBookIdAndUserId(@Param("completed") String completed, @Param("bookId") Long bookId, @Param("userId") Long userId);

    int updateReviewByBookIdAndUserId(@Param("review") String review, @Param("bookId") Long bookId, @Param("userId") Long userId);

}




