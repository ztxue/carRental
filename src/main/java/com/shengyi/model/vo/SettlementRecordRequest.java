package com.shengyi.model.vo;

import lombok.Data;

@Data
public class SettlementRecordRequest {
    private Long id;

    private Long carId;

    private Long CustomerId;

    private Double income;

    private Double expend;
}
