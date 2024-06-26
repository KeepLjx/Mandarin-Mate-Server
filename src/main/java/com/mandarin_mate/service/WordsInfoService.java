package com.mandarin_mate.service;

import com.mandarin_mate.pojo.WordsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mandarin_mate.utils.Result;

/**
* @author lenovo
* @description 针对表【words_info(单词相关信息表)】的数据库操作Service
* @createDate 2023-10-27 23:02:09
*/
public interface WordsInfoService extends IService<WordsInfo> {

    /**
     * 获取词书中的单词详细信息
     * @param bookId
     * @return
     */
    Result getBookInfo(Long bookId);
}
