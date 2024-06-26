package com.mandarin_mate.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName words_info
 */
@TableName(value ="words")
@Data
public class WordsInfo implements Serializable {
    private Long wordsId;

    private Long bookId;

    private String wordsSpell;

    private String wordsPinyin;

    private String example;

    private String content;

    private String typeTag;

    private String voicePath;

    private static final long serialVersionUID = 1L;
}