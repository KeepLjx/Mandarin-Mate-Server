package com.mandarin_mate.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @TableName collection
 */
@TableName(value ="collection")
@Builder
@Data
public class Collection implements Serializable {
    private Long userId;

    private String wordsId;

    private static final long serialVersionUID = 1L;
}