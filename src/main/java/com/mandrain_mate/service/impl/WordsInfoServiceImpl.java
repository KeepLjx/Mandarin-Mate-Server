package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.WordsInfo;
import com.mandrain_mate.service.WordsInfoService;
import com.mandrain_mate.mapper.WordsInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【words_info(单词相关信息表)】的数据库操作Service实现
* @createDate 2023-10-27 23:02:09
*/
@Service
public class WordsInfoServiceImpl extends ServiceImpl<WordsInfoMapper, WordsInfo>
    implements WordsInfoService{

}




