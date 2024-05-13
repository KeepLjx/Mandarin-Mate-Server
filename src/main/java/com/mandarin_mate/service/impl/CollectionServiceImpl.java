package com.mandarin_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandarin_mate.pojo.Collection;
import com.mandarin_mate.service.CollectionService;
import com.mandarin_mate.mapper.CollectionMapper;
import com.mandarin_mate.utils.JwtHelper;
import com.mandarin_mate.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
* @author lenovo
* @description 针对表【collection(用户收藏表)】的数据库操作Service实现
* @createDate 2023-10-27 23:01:55
*/
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

    @Resource
    private JwtHelper jwtHelper;

    @Resource
    private CollectionMapper collectionMapper;
    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    /**
     * 用户收藏单词
     * @param wordsId
     * @param token
     * @return
     */
    @Override
    public boolean collection(String wordsId, String token) {
        Long userId = jwtHelper.getUserId(token);
        String Id = collectionMapper.selectWordsIdByUserId(userId);
        if (Id == null || Id.isEmpty()) {
            //插入新数据
            boolean b = collectionMapper.insertUserIdAndWordsId(userId, wordsId);
            return b;
        }
        int i = collectionMapper.updateWordsIdByUserId(Id + "," + wordsId, userId);
        return i != 0;
    }

    /**
     * 删除收藏单词接口
     * @param wordsId
     * @param token
     * @return
     */
    @Override
    public boolean delete(String wordsId, String token) {
        boolean isSuccess = false;
        Long userId = jwtHelper.getUserId(token);
        String Id = collectionMapper.selectWordsIdByUserId(userId);
        if (!Id.contains(",")) {
            int i = collectionMapper.updateWordsIdByUserId("", userId);
            isSuccess = (i != 0);
            return isSuccess;
        } else {
            int exist = Id.indexOf(wordsId);
            if (exist == 0) {
                Id = Id.substring(0, exist) + Id.substring(exist + wordsId.length() + 1);
                int i = collectionMapper.updateWordsIdByUserId(Id, userId);
                isSuccess = (i != 0);
            } else {
                Id = Id.substring(0, exist - 1) + Id.substring(exist + wordsId.length());
                int i = collectionMapper.updateWordsIdByUserId(Id, userId);
                isSuccess = (i != 0);
            }
        }
        return isSuccess;
    }

    /**
     * 获取用户收藏单词表
     * @param token
     * @return
     */
    @Override
    public Result getCollection(String token) {
        String wordsId = collectionMapper.selectWordsIdByUserId(jwtHelper.getUserId(token));
        HashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("collections", wordsId);
        map.put("length", wordsId.split(",").length);
        return Result.ok(map);
    }
}




