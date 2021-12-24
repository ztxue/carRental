package com.shengyi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengyi.common.BasicCode;
import com.shengyi.common.Result;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.common.result.PageResult;
import com.shengyi.mapper.*;
import com.shengyi.model.CarData;
import com.shengyi.model.CarRecord;
import com.shengyi.model.vo.CarRecordRequest;
import com.shengyi.model.vo.OptionRequest;
import com.shengyi.model.vo.SettlementRecordRequest;
import com.shengyi.model.dto.CarRecordDto;
import com.shengyi.model.dto.ClassOptions;
import com.shengyi.service.CarRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@SuppressWarnings("unchecked")
@Service
public class CarRecordServiceImpl implements CarRecordService {
    @Resource
    private CarRecordMapper carRecordMapper;

    @Resource
    private CarClassMapper carClassMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CarDataMapper carDataMapper;

//    @Resource
//    private CarFinanceMapper carFinanceMapper;

    private PageResult<CarRecordDto> setPage(Page page, PageResult<CarRecordDto> pageResult){
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
    public PageResult<CarRecordDto> getCarRecordByTime(CarRecordRequest recordRequest) {
        System.out.println("vo"+recordRequest);
        if (recordRequest == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        PageHelper.startPage(recordRequest.getPageNum(),recordRequest.getPageSize());
        List<CarRecordDto> list = carRecordMapper.findRecordByTime(recordRequest);
        Page<CarRecordDto> page = (Page<CarRecordDto>) list;
        PageResult<CarRecordDto> result = new PageResult<>();
        return setPage(page,result);
    }

    @Override
    public Result<Boolean> removeRecordById(Long id) {
        if(id == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (id < 1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        Integer count = carRecordMapper.deleteRecordById(id);
        if (count < 1){
            throw new ServiceException(BasicCode.DB_DELETE_FAILED);
        }
        return new Result<Boolean>().success(true);
    }

    @Override
    public Result<List<ClassOptions>> getOptions() {
        return new Result<List<ClassOptions>>().success(carClassMapper.findRecordOptions());
    }
    //获取订单结算时的顾客列表
    @Override
    public Result<List<OptionRequest>> getCustomerOptions(String customerName) {
        if (customerName == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        return new Result<List<OptionRequest>>().
                success(customerMapper.findOptionsByName(customerName));
    }
    //通过id获取订单结算时的汽车列表
    @Override
    public Result<CarData> getCarDataById(Long id) {
        if (id == null || id<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        return new Result<CarData>().success(carDataMapper.findCarDataById(id));
    }
    //添加租赁记录
    @Override
    public Result<Boolean> addRecord(CarRecord carRecord) {
        if (carRecord == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer recordId = carRecordMapper.insertRecord(carRecord);
        if (recordId == null){
            throw new ServiceException(BasicCode.DB_INSERT_FAILED);
        }
        Integer count = carDataMapper.updateStatusById(false,"租赁中",carRecord.getCarId());
        if (count < 1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        return new Result<Boolean>().success(true);
    }
    //获取时间
    @Override
    public Result<Date> getCurrentDate() {
        Date sysDate = carRecordMapper.selectSysDate();
        Result<Date> result = new Result<>();
        return result.success(sysDate);
    }
    //结算
    @Override
    public Result<Boolean> settlementRecord(SettlementRecordRequest recordRequest) {
        System.out.println(recordRequest);
        if (recordRequest == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        //
        Integer count = carRecordMapper.updateStatusById(recordRequest.getId());
        if (count < 1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        //
        count = carDataMapper.updateStatusById(true,"已启用",recordRequest.getCarId());
        if (count < 1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        //
        count = customerMapper.updateLeaseNumById(recordRequest.getCustomerId());
        if (count < 1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        return new Result<Boolean>().success(true);
    }

}
