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
public class WordsInfo {
    // 单词id
    private BigInteger wordsId;
    // 词书id
    private BigInteger bookId;
    // 拼音拼写
    private String wordsSpell;
    // 拼音发音
    private String example;
    // 例句
    private String content;
    // 单词种类
    private String typeTag;
}
