package com.shengyi.controller;

import com.shengyi.common.Result;
import com.shengyi.common.result.PageResult;
import com.shengyi.model.vo.*;
import com.shengyi.model.dto.CarDataDto;
import com.shengyi.model.dto.CarStockDetailsDto;
import com.shengyi.model.dto.CarStockDto;
import com.shengyi.service.CarDataService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/data/")
public class CarDataController {
    @Autowired
    private CarDataService carDataService;

    @PostMapping(value = "getAllStock")
    @ResponseBody
    public PageResult<CarStockDto> getAllStock(@RequestBody PageRequest request){
        return carDataService.getAllStock(request);
    }

    @PostMapping(value = "getStockDetails")
    @ResponseBody
    public PageResult<CarStockDetailsDto> getStockDetails(@RequestBody StockDetailsRequest  request){
        return carDataService.getStockDetails(request);
    }

    @PostMapping(value = "getAllCarData")
    @ResponseBody
    public PageResult<CarDataDto> getAllCarData(@RequestBody PageRequest request){
        return carDataService.getAllCarData(request);
    }

    @PostMapping(value = "searchCarData")
    @ResponseBody
    public PageResult<CarDataDto> searchCarData(@RequestBody CarDataSearchRequest request){
        return carDataService.searchCarDataByBrand(request);
    }

    @PostMapping(value = "modifyStatus")
    @ResponseBody
    public Result<Boolean> modifyStatus(@RequestBody CarDataStatusRequest request){
        return carDataService.updateCarStatus(request);
    }

    @PostMapping(value = "removeCarData")
    @ResponseBody
    public Result<Boolean> removeCarData(@Param("id") Long id){
        return carDataService.removeCarData(id);
    }

    @PostMapping(value = "getUserOptions")
    @ResponseBody
    public Result<List<OptionRequest>> getUserOptions(){
        return carDataService.getUserOptions();
    }

    @PostMapping(value = "getClassOptions")
    @ResponseBody
    public Result<List<OptionRequest>> getClassOptions(){
        return carDataService.getClassOptions();
    }

    @PostMapping(value = "modifyCarData")
    @ResponseBody
    public Result<Boolean> modifyCarData(@RequestBody CarDataDto carDataDto){
        return carDataService.updateCarData(carDataDto);
    }

    @PostMapping(value = "addCarData")
    @ResponseBody
    public Result<Boolean> addCarData(@RequestBody CarDataDto carDataDto){
        return carDataService.addCarData(carDataDto);
    }
}
