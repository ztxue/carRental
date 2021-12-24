package com.shengyi.service;

import com.shengyi.common.Result;
import com.shengyi.common.result.PageResult;
import com.shengyi.model.vo.*;
import com.shengyi.model.dto.CarDataDto;
import com.shengyi.model.dto.CarStockDetailsDto;
import com.shengyi.model.dto.CarStockDto;

import java.util.List;

public interface CarDataService {
    PageResult<CarStockDto> getAllStock (PageRequest request);

    PageResult<CarStockDetailsDto> getStockDetails(StockDetailsRequest request);

    PageResult<CarDataDto> getAllCarData(PageRequest request);

    Result<Boolean> removeCarData(Long id);

    Result<Boolean> updateCarData(CarDataDto carDataDto);

    Result<Boolean> updateCarStatus(CarDataStatusRequest request);

    Result<Boolean> addCarData(CarDataDto carDataDto);

    PageResult<CarDataDto> searchCarDataByBrand(CarDataSearchRequest request);

    Result<List<OptionRequest>> getUserOptions();

    Result<List<OptionRequest>> getClassOptions();
}
