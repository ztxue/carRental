package com.shengyi.model;

import lombok.Data;

import java.util.Date;
@Data
public class CarCustomer {
    private Long Id;

    private String customerName;

    /**
     * 身份证号
     */
    private String number;

    private String phone;

    /**
     * 租赁次数
     */
    private Integer leaseNum;

    private String sex;

    private Integer age;

    private String address;

    private Date createTime;

    private Date updateTime;
}
