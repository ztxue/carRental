package com.shengyi.model;

import lombok.Data;

import java.util.Date;

/**
 * 类别类
 */

@Data
public class CarClass {
    private Long id;

    private String className;

    private Date createTime;

    private Date updateTime;
}
