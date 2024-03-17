package com.mandrain_mate.pojo.vo;

import com.mandrain_mate.pojo.Book;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @BelongsProject: Mandarin-Mate-Server
 * @BelongsPackage: com.mandrain_mate.pojo.vo
 * @Author: kc
 * @CreateTime: 2023-11-01  01:07
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Builder
public class BookVO {
    private String bookLevel;
    private LinkedList<Book> books;
}
