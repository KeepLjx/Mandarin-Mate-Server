package com.mandarin_mate.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName book
 */

@TableName(value ="book")
@Data
public class Book implements Serializable {
    private Long bookId;

    private String bookImg;

    private String bookIntro;

    private String bookName;

    private String bookLevel;

    private static final long serialVersionUID = 1L;
}