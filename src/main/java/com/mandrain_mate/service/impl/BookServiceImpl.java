package com.mandrain_mate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mandrain_mate.pojo.Book;
import com.mandrain_mate.pojo.vo.BookVO;
import com.mandrain_mate.service.BookService;
import com.mandrain_mate.mapper.BookMapper;
import com.mandrain_mate.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
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
        BookVO pinYin = BookVO.builder().bookLevel("PinYin").books(new LinkedList<>()).build();
        BookVO primary = BookVO.builder().bookLevel("primary").books(new LinkedList<>()).build();
        BookVO intermediate = BookVO.builder().bookLevel("intermediate").books(new LinkedList<>()).build();
        BookVO senior = BookVO.builder().bookLevel("senior").books(new LinkedList<>()).build();
        books.forEach(item->{
            if(item.getBookLevel().equals("0")){
                pinYin.getBooks().add(item);
            }
            if(item.getBookLevel().equals("1")){
                primary.getBooks().add(item);
            }
            if(item.getBookLevel().equals("2")){
                intermediate.getBooks().add(item);
            }
            if(item.getBookLevel().equals("3")){
                senior.getBooks().add(item);
            }
        });
        LinkedList<Object> objects = new LinkedList<>();
        objects.add(pinYin);
        objects.add(primary);
        objects.add(intermediate);
        objects.add(senior);
        HashMap<Object, Object> data = new HashMap<>();
        data.put("bookInfo",objects);
        return Result.ok(data);
    }
}




