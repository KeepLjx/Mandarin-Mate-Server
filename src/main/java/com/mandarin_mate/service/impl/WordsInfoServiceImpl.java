package com.mandarin_mate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandarin_mate.pojo.WordsInfo;
import com.mandarin_mate.service.WordsInfoService;
import com.mandarin_mate.mapper.WordsInfoMapper;
import com.mandarin_mate.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author lenovo
* @description 针对表【words_info(单词相关信息表)】的数据库操作Service实现
* @createDate 2023-10-27 23:02:09
*/
@Service
public class WordsInfoServiceImpl extends ServiceImpl<WordsInfoMapper, WordsInfo>
    implements WordsInfoService{

    @Autowired
    private WordsInfoMapper wordsInfoMapper;
    /**
     * 获取词书中的单词详细信息
     * @param bookId
     * @return
     */
    @Override
    public Result getBookInfo(Long bookId) {
        LambdaQueryWrapper<WordsInfo> bookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bookLambdaQueryWrapper.eq(WordsInfo::getBookId,bookId);
        bookLambdaQueryWrapper.orderByAsc(WordsInfo::getWordsId);
        List<WordsInfo> wordsInfos = wordsInfoMapper.selectList(bookLambdaQueryWrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("wordsInfo",wordsInfos);
        return Result.ok(map);
    }
}




