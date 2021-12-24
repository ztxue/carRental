package com.shengyi.controller;

import com.shengyi.common.Result;
import com.shengyi.model.CarUser;
import com.shengyi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register/")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping(value = "addUser")
    @ResponseBody
    public Result<Boolean> addUser(@RequestBody CarUser carUser){
        System.out.println("carUser:"+carUser);
        Result<Boolean> result = new Result<>();
        return result.success(registerService.addUser(carUser));
    }
}
