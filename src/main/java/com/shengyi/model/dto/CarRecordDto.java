package com.shengyi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shengyi.model.CarCustomer;
import com.shengyi.model.CarData;
import com.shengyi.model.CarUser;
import lombok.Data;

import java.util.Date;

@Data

public class CarRecordDto {
    private Long id;

    private CarCustomer customer;

    private String timeLong;

    /**
     * 此订单是否已完成
     */
    private Boolean status;

    /**
     * 押金
     */
    private Double deposit;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    private CarData carData;

    private CarUser carUser;
}
