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
public class Collection {
    // 用户id
    private BigInteger userId;
    // 单词id
    private BigInteger wordsId;
}
