package com.shengyi.model;

import lombok.Data;

import java.util.Date;
@Data
public class CarData {
    private Long id;

    /**
     * 车牌号
     */
    private String number;

    private Boolean status;

    /**
     * 状态描述
     */
    private String statusDescribe;

    private String color;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 座位数
     */
    private Integer seating;

    /**
     * 购入日期
     */
    private Date buyDate;

    /**
     * 日租价格
     */
    private Double dayPrice;

    /**
     * 月租价格
     */
    private Double monthPrice;

    /**
     * 已跑里程
     */
    private Integer runKm;

    /**
     * 类别ID
     */
    private Long classId;

    /**
     * 管理人员ID
     */
    private Long userId;

    private Date createTime;

    private Date updateTime;
}
