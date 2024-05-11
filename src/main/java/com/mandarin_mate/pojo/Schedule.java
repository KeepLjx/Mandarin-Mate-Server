package com.mandarin_mate.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName schedule
 */
@TableName(value ="schedule")
@Data
public class Schedule implements Serializable {
    private Long userId;

    private Long bookId;

    private Integer isDelete;

    private String completed;

    private String review;

    private static final long serialVersionUID = 1L;
}