package com.shengyi.model.vo;

import lombok.Data;

@Data
public class CarDataStatusRequest {
    private Long id;

    private Boolean status;

    private String statusDescribe;
}
