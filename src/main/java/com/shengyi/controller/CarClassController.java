package com.shengyi.controller;

import com.shengyi.common.Result;
import com.shengyi.model.CarClass;
import com.shengyi.service.CarClassService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/class/")
public class CarClassController {
    @Autowired
    CarClassService carClassService;

    @PostMapping("getAllClass")
    @ResponseBody
    public Result<List<CarClass>> getAllClass(){
        return carClassService.getAllClass();
    }

    @PostMapping("addClass")
    @ResponseBody
    public Result<Boolean> addClass(@Param("className") String className){
        return carClassService.addClass(className);
    }

    @PostMapping("modifyClass")
    @ResponseBody
    public Result<Boolean> modifyClass(@RequestBody CarClass carClass){
        return carClassService.modifyClassNameById(carClass);
    }

    @PostMapping("removeClass")
    @ResponseBody
    public Result<Boolean> removeClassAndCarData(@Param("classId")Long classId){
        return carClassService.removeClassAndCarData(classId);
    }
}
