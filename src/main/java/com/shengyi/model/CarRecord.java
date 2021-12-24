package com.shengyi.model;

import lombok.Data;

import java.util.Date;

/**
 * 租赁记录
 */
@Data
public class CarRecord {
    private Long id;
    /**
     * 客户Id
     */
    private Long customerId;

    private Long carId;

    /**
     * 租赁时长
     */
    private String timeLong;

    /**
     * 此订单是否已完成
     */
    private Boolean status;

    /**
     * 押金
     */
    private Double deposit;

    private Long userId;


    private Date createTime;

    private Date updateTime;


}
