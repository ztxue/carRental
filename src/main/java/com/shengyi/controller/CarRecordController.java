package com.shengyi.controller;

import com.shengyi.common.Result;
import com.shengyi.common.result.PageResult;
import com.shengyi.model.CarData;
import com.shengyi.model.CarRecord;
import com.shengyi.model.vo.CarRecordRequest;
import com.shengyi.model.vo.OptionRequest;
import com.shengyi.model.vo.SettlementRecordRequest;
import com.shengyi.model.dto.CarRecordDto;
import com.shengyi.model.dto.ClassOptions;
import com.shengyi.service.CarRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/record/")
public class CarRecordController {
    @Autowired
    private CarRecordService carRecordService;

    @PostMapping(value = "getRecordByTime")
    @ResponseBody
    public PageResult<CarRecordDto> getRecordByTime(@RequestBody CarRecordRequest carRecordRequest){
        return carRecordService.getCarRecordByTime(carRecordRequest);
    }

    @PostMapping(value = "removeRecordById")
    @ResponseBody
    public Result<Boolean> removeRecordById(@Param("id") Long id){
        return carRecordService.removeRecordById(id);
    }

    @PostMapping(value = "getOptions")
    @ResponseBody
    public Result<List<ClassOptions>> getOptios(){
        return carRecordService.getOptions();
    }

    @PostMapping(value = "getCustomerOptions")
    @ResponseBody
    public Result<List<OptionRequest>> getCustomerOptions(@Param("customerName") String customerName){
        return carRecordService.getCustomerOptions(customerName);
    }

    @PostMapping(value = "getCurrentCarData")
    @ResponseBody Result<CarData> getCurrentCarData(@Param("id") Long id){
        return carRecordService.getCarDataById(id);
    }

    @PostMapping(value = "addCarRecord")
    @ResponseBody Result<Boolean> addCarRecord(@RequestBody CarRecord carRecord){
        return carRecordService.addRecord(carRecord);
    }

    @PostMapping(value = "getCurrentDate")
    @ResponseBody Result<Date> getCurrentDate(){
        return carRecordService.getCurrentDate();
    }

    @PostMapping(value = "settlement")
    @ResponseBody Result<Boolean> settlement(@RequestBody SettlementRecordRequest recordRequest){
        return carRecordService.settlementRecord(recordRequest);
    }
}
