package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.Book;
import com.mandrain_mate.service.BookService;
import com.mandrain_mate.mapper.BookMapper;
import com.mandrain_mate.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author lenovo
* @description 针对表【book(图书信息相关表)】的数据库操作Service实现
* @createDate 2023-10-27 23:01:49
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

    @Autowired
    private BookMapper bookMapper;

    /**
     * 获取图书所有信息业务实现
     * @return
     */
    @Override
    public Result getBookInfo() {
        List<Book> books = bookMapper.selectList(null);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("bookInfo",books);
        return Result.ok(map);
    }
}




