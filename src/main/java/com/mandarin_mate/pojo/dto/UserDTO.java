package com.mandarin_mate.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
public class UserDTO {

    private Long userId;

    private String nickName;

    private String password;

    private String avatarPath;

    private Long bookId;

    private Integer learningLevel;

    private String phone;

    private String userMail;

    public UserDTO(Long bookId) {
        this.bookId = bookId;
    }
}


