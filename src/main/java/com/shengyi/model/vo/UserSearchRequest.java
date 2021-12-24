package com.shengyi.model.vo;

import lombok.Data;

@Data
public class UserSearchRequest {
    private String userName;

    private Integer pageNum;

    private Integer pageSize;
}
