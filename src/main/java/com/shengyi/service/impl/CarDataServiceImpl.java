package com.shengyi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengyi.common.BasicCode;
import com.shengyi.common.Result;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.common.result.PageResult;
import com.shengyi.mapper.CarClassMapper;
import com.shengyi.mapper.CarDataMapper;
import com.shengyi.mapper.CarUserMapper;
import com.shengyi.model.vo.*;
import com.shengyi.model.dto.CarDataDto;
import com.shengyi.model.dto.CarStockDetailsDto;
import com.shengyi.model.dto.CarStockDto;
import com.shengyi.service.CarDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@SuppressWarnings("unchecked")
@Service
public class CarDataServiceImpl implements CarDataService {
    @Resource
    private CarDataMapper carDataMapper;

    @Resource
    private CarUserMapper carUserMapper;

    @Resource
    private CarClassMapper carClassMapper;

    @Override
    public PageResult<CarStockDto> getAllStock(PageRequest request) {
        if (request == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageNum() <1 || request.getPageSize()<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<CarStockDto> list = carDataMapper.findAllStock();
        Page<CarStockDto> page = (Page<CarStockDto>) list;
        PageResult<CarStockDto> pageResult = new PageResult<>();
        if (page != null){
            pageResult.setTotalPage(page.getPages());
            pageResult.setTotal(page.getTotal());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setPageNum(page.getPageNum());
            pageResult.setDataList(page.getResult());
        }
        return pageResult;
    }

    @Override
    public PageResult<CarStockDetailsDto> getStockDetails(StockDetailsRequest request) {
        if (request == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageNum() <1 || request.getPageSize()<1 || request.getClassId()<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<CarStockDetailsDto> list = carDataMapper.findStockDetails(request.getClassId());
        Page<CarStockDetailsDto> page = (Page<CarStockDetailsDto>) list;
        PageResult<CarStockDetailsDto> pageResult = new PageResult<>();
        if (page != null){
            pageResult.setTotalPage(page.getPages());
            pageResult.setTotal(page.getTotal());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setPageNum(page.getPageNum());
            pageResult.setDataList(page.getResult());
        }
        return pageResult;
    }

    @Override
    public PageResult<CarDataDto> getAllCarData(PageRequest request) {
        if (request == null ){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageSize()<1 || request.getPageNum()<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<CarDataDto> list = carDataMapper.findAllData();
        Page<CarDataDto> page = (Page<CarDataDto>) list;
        PageResult<CarDataDto> pageResult = new PageResult<>();
        if (page != null){
            pageResult.setTotalPage(page.getPages());
            pageResult.setTotal(page.getTotal());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setPageNum(page.getPageNum());
            pageResult.setDataList(page.getResult());
        }
        return pageResult;
    }

    @Override
    public Result<Boolean> removeCarData(Long id) {
        if(id == null || id<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        Integer count = carDataMapper.deleteCarDataById(id);
        if (count<1){
            throw new ServiceException(BasicCode.DB_DELETE_FAILED);
        }
        Result<Boolean> result = new Result();
        return result.success(true);
    }

    @Override
    public Result<Boolean> updateCarData(CarDataDto carDataDto) {
        if (carDataDto == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = carDataMapper.updateCarData(carDataDto);
        if (count<1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        return new Result<Boolean>().success(true);
    }

    @Override
    public Result<Boolean> updateCarStatus(CarDataStatusRequest request) {
        if (request == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = carDataMapper.updateStatus(request);
        if (count<1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        return new Result<Boolean>().success(true);
    }

    @Override
    public Result<Boolean> addCarData(CarDataDto carDataDto) {
        if (carDataDto == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = carDataMapper.insertCarData(carDataDto);
        if (count<1){
            throw new ServiceException(BasicCode.DB_INSERT_FAILED);
        }
        return new Result<Boolean>().success(true);
    }

    @Override
    public PageResult<CarDataDto> searchCarDataByBrand(CarDataSearchRequest request) {
        if (request == null ){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageSize()<1 || request.getPageNum()<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<CarDataDto> list = carDataMapper.findCarDataByBrand(request.getBrand());
        Page<CarDataDto> page = (Page<CarDataDto>) list;
        PageResult<CarDataDto> pageResult = new PageResult<>();
        if (page != null){
            pageResult.setTotalPage(page.getPages());
            pageResult.setTotal(page.getTotal());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setPageNum(page.getPageNum());
            pageResult.setDataList(page.getResult());
        }
        return pageResult;
    }

    @Override
    public Result<List<OptionRequest>> getUserOptions() {
        List<OptionRequest> list = carUserMapper.findOptions();
        if (list == null){
            throw new ServiceException(BasicCode.DB_DATA_NOT_FOUND);
        }
        return new Result<List<OptionRequest>>().success(list);
    }

    @Override
    public Result<List<OptionRequest>> getClassOptions() {
        List<OptionRequest> list = carClassMapper.findOptions();
        if (list == null){
            throw new ServiceException(BasicCode.DB_DATA_NOT_FOUND);
        }
        return new Result<List<OptionRequest>>().success(list);
    }

}
