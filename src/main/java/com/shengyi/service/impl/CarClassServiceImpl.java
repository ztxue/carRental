package com.shengyi.service.impl;

import com.shengyi.common.BasicCode;
import com.shengyi.common.Result;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.mapper.CarClassMapper;
import com.shengyi.mapper.CarDataMapper;
import com.shengyi.model.CarClass;
import com.shengyi.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CarClassServiceImpl implements CarClassService {
    @Autowired
    private CarClassMapper carClassMapper;

    @Autowired
    private CarDataMapper carDataMapper;

    @Override
    public Result<List<CarClass>> getAllClass() {
        List<CarClass> list = carClassMapper.findAllClass();
        Result<List<CarClass>> result = new Result<>();
        return result.success(list);
    }

    @Override
    public Result<Boolean> addClass(String className) {
        if (className == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = carClassMapper.insertClass(className);
        Result<Boolean> result = new Result<>();
        if (count<1){
            throw new ServiceException(BasicCode.DB_INSERT_FAILED);
        }else {
            return result.success(true);
        }
    }

    @Override
    public Result<Boolean> modifyClassNameById(CarClass carClass) {
        if (carClass == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = carClassMapper.updateClassNameById(carClass);
        Result<Boolean> result = new Result<>();
        if (count<1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        } else {
          return result.success(true);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Result<Boolean> removeClassAndCarData(Long classId) {
        if (classId == null || classId<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        Integer count = carClassMapper.deleteClassById(classId);
        carDataMapper.deleteCarDataByClassId(classId);
        if (count<1){
            throw new ServiceException(BasicCode.DB_DELETE_FAILED);
        }
        Result<Boolean> result = new Result<>();
        return result.success(true);
    }
}
