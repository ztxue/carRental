package com.shengyi.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class CarRecordRequest {
    private Integer pageNum;

    private Integer pageSize;

    private Boolean Status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endTime;
}
