package com.shengyi.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String userName;

    private String phone;

    private String jobNum;

    private String email;

    private String sex;
}
