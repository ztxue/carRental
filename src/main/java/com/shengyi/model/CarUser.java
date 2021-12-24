package com.shengyi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class CarUser {
    private Long id;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private String sex;

    private Long jobNum;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;
}
