package com.mandarin_mate.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/3/13
 * @version:1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {

    private String openid;

    private String token;

    private LocalDateTime createTime;
}
