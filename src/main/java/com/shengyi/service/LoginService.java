package com.shengyi.service;

import com.shengyi.model.CarUser;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    CarUser login(CarUser carUser, HttpServletResponse httpServletResponse);

    Boolean checkCokie(HttpServletRequest request,String name);
}
