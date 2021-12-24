package com.shengyi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class CustomerDto {
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

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;
}
