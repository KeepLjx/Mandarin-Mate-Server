package com.mandarin_mate.mapper;
import org.apache.ibatis.annotations.Param;

import com.mandarin_mate.pojo.WordsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author lenovo
* @description 针对表【words_info(单词相关信息表)】的数据库操作Mapper
* @createDate 2023-10-27 23:02:09
* @Entity com.mandrain_mate.pojo.WordsInfo
*/
@Repository
public interface WordsInfoMapper extends BaseMapper<WordsInfo> {


    WordsInfo selectAllByWordsId(@Param("wordsId") Long wordsId);
}




