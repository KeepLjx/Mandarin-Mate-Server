package com.mandarin_mate.pojo.vo;

import com.mandarin_mate.pojo.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    private String bookLevel;
    private LinkedList<Book> books;
}
