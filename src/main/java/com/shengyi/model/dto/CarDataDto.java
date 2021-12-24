package com.shengyi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CarDataDto {
    private Long id;

    private String brand;

    private String color;

    private Integer seating;

    private String number;

    private Double dayPrice;

    private Double monthPrice;

    private Integer runKm;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buyDate;

    private String ClassName;

    private String userName;

    private Boolean status;

    private String statusDescribe;

    private Long classId;

    private Long userId;
}
