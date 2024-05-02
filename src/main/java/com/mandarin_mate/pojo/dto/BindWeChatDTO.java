package com.mandarin_mate.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther Keepl
 * @description
 * @Version 1.0.0
 * @date 2024/5/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BindWeChatDTO {

    private Long userId;

    private String code;

    private String openId;

    public BindWeChatDTO(Long userId, String openId) {
        this.userId = userId;
        this.openId = openId;
    }
}
