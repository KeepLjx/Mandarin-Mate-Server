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
public class Schedule {
    // 用户id
    private BigInteger userId;
    // 图书id
    private BigInteger bookId;
    // 是否删除 1:是 0:否
    private int isDelete;
    // 单词完成进度
    private BigInteger completed;
}
