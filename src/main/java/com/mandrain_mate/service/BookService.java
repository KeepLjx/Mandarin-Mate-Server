package com.mandrain_mate.service;

import com.mandrain_mate.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mandrain_mate.utils.Result;

/**
* @author lenovo
* @description 针对表【book(图书信息相关表)】的数据库操作Service
* @createDate 2023-10-27 23:01:49
*/
public interface BookService extends IService<Book> {

    /**
     * 获取词数所有信息业务
     * @return
     */
    Result getBookInfo();
}
