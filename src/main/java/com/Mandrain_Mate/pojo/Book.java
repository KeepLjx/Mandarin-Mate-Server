package com.Mandrain_Mate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author:CookieBoy
 * @Description
 * @create 2023/10/27
 * @version:1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    // 图书id
    private BigInteger bookId;
    // 图书封面
    private String bookImg;
    // 图书简介
    private String bookIntro;
    // 图书名称
    private String bookName;
    // 图书难易等级
    private String bookLevel;
}
