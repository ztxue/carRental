package com.shengyi.model.vo;

import lombok.Data;

@Data
public class StockDetailsRequest {
    private Integer pageNum;

    private Integer pageSize;

    private Long classId;
}
