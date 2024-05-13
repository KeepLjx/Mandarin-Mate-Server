package com.mandarin_mate.service;

import com.mandarin_mate.pojo.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mandarin_mate.utils.Result;
import org.apache.ibatis.io.ResolverUtil;

/**
* @author lenovo
* @description 针对表【collection(用户收藏表)】的数据库操作Service
* @createDate 2023-10-27 23:01:55
*/
public interface CollectionService extends IService<Collection> {

    boolean collection(String wordsId, String token);

    boolean delete(String wordsId, String token);

    Result getCollection(String token);
}
