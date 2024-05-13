package com.mandarin_mate.controller;

import com.mandarin_mate.service.CollectionService;
import com.mandarin_mate.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.web.bind.annotation.*;

/**
 * @auther Keepl
 * @description
 * @Version 1.0.0
 * @date 2024/5/13
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    /**
     * 收藏单词接口
     * @param wordsId
     * @param token
     * @return
     */
    @PostMapping("/collection")
    public Result collection(@RequestParam Long wordsId, @RequestHeader String token) {
        boolean result = collectionService.collection(String.valueOf(wordsId), token);
        return Result.ok(result ? "Have been collected." : "Collection failure.");
    }

    /**
     * 用户删除收藏单词接口
     * @param wordsId
     * @param token
     * @return
     */
    @PostMapping("/deleteCollection")
    public Result delete(@RequestParam Long wordsId, @RequestHeader String token) {
        boolean result = collectionService.delete(String.valueOf(wordsId), token);
        return Result.ok(result ? "Successfully deleted." : "Deletion failure");
    }


    /**
     * 获取用户收藏表中的单词Id
     * @param token
     * @return
     */
    @GetMapping("/getCollection")
    public Result getCollection (@RequestHeader String token) {
        Result collection = collectionService.getCollection(token);
        return collection;
    }

}
