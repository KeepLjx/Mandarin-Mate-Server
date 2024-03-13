package com.mandarin_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandarin_mate.pojo.Collection;
import com.mandarin_mate.service.CollectionService;
import com.mandarin_mate.mapper.CollectionMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【collection(用户收藏表)】的数据库操作Service实现
* @createDate 2023-10-27 23:01:55
*/
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

}




