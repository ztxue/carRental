package com.shengyi.service;

import com.shengyi.common.Result;
import com.shengyi.model.CarClass;

import java.util.List;

public interface CarClassService {
    Result<List<CarClass>> getAllClass();

    Result<Boolean> addClass(String className);

    Result<Boolean> modifyClassNameById(CarClass carClass);

    Result<Boolean> removeClassAndCarData(Long classId);
}
