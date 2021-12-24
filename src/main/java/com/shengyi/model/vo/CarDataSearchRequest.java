package com.shengyi.model.vo;

import lombok.Data;

@Data
public class CarDataSearchRequest {
    private Integer pageNum;

    private Integer pageSize;

    private String brand;
}
