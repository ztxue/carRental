package com.shengyi.controller;

import com.shengyi.common.Result;
import com.shengyi.model.CarUser;
import com.shengyi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ResponseBody
    @PostMapping(value = "login")
    public Result<CarUser> login(@RequestBody CarUser carUser, HttpServletResponse response){
        System.out.println("carUser"+carUser);
        Result<CarUser> result = new Result<>();
        return result.success(loginService.login(carUser, response));
    }
}
