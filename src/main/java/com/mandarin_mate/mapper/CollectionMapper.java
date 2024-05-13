package com.mandarin_mate.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.mandarin_mate.pojo.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author lenovo
* @description 针对表【collection(用户收藏表)】的数据库操作Mapper
* @createDate 2023-10-27 23:01:55
* @Entity com.mandrain_mate.pojo.Collection
*/
@Repository
public interface CollectionMapper extends BaseMapper<Collection> {

    /** 根据用户Id查询wordsId */
    String selectWordsIdByUserId(@Param("userId") Long userId);

    /** 根据用户Id更新wordsId */
    int updateWordsIdByUserId(@Param("wordsId") String wordsId, @Param("userId") Long userId);

    /** 新建用户收藏数据 */
    boolean insertUserIdAndWordsId(Long userId, String wordsId);
}




