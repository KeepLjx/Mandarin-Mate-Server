package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.Book;
import com.mandrain_mate.service.BookService;
import com.mandrain_mate.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【book(图书信息相关表)】的数据库操作Service实现
* @createDate 2023-10-27 23:01:49
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

}




