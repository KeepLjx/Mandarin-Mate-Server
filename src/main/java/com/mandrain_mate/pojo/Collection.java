package com.mandrain_mate.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName collection
 */
@TableName(value ="collection")
@Data
public class Collection implements Serializable {
    private Long userId;

    private Long wordsId;

    private static final long serialVersionUID = 1L;
}