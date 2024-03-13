package com.mandarin_mate.mapper;

import com.mandarin_mate.pojo.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author lenovo
* @description 针对表【book(图书信息相关表)】的数据库操作Mapper
* @createDate 2023-10-27 23:01:49
* @Entity com.mandrain_mate.pojo.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}




