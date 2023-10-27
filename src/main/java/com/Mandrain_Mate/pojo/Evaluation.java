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
public class Evaluation {
    // 用户id
    private BigInteger userId;
    // 拼音id
    private BigInteger wordsId;
    // 得分
    private BigInteger score;
}
