package com.shengyi.model.vo;

import lombok.Data;

@Data
public class CustomerSearchRequest {
    private Integer pageNum;
    private Integer pageSize;
    private String keywords;
}
