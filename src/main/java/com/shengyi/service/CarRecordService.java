package com.shengyi.service;

import com.shengyi.common.Result;
import com.shengyi.common.result.PageResult;
import com.shengyi.model.CarData;
import com.shengyi.model.CarRecord;
import com.shengyi.model.vo.CarRecordRequest;
import com.shengyi.model.vo.OptionRequest;
import com.shengyi.model.vo.SettlementRecordRequest;
import com.shengyi.model.dto.CarRecordDto;
import com.shengyi.model.dto.ClassOptions;

import java.util.Date;
import java.util.List;

public interface CarRecordService {
    PageResult<CarRecordDto> getCarRecordByTime(CarRecordRequest recordRequest);

    Result<Boolean> removeRecordById(Long id);

    Result<List<ClassOptions>> getOptions();

    Result<List<OptionRequest>> getCustomerOptions(String customerName);

    Result<CarData> getCarDataById(Long id);

    Result<Boolean> addRecord(CarRecord carRecord);

    Result<Date> getCurrentDate();

    Result<Boolean> settlementRecord(SettlementRecordRequest recordRequest);

}
