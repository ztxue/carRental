package com.shengyi.service.impl;

import com.shengyi.common.BasicCode;
import com.shengyi.common.util.CookieUtil;
import com.shengyi.common.util.JwtUtil;
import com.shengyi.common.util.MD5Util;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.mapper.CarUserMapper;
import com.shengyi.model.CarUser;
import com.shengyi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    CarUserMapper carUserMapper;
    @Override
    public CarUser login(CarUser carUser, HttpServletResponse httpServletResponse) {
        if (carUser == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        CarUser user;
        if (carUser.getEmail() != null){
            user = carUserMapper.findUserByEmail(carUser.getEmail());
        }else{
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if(carUser.getPassword() != null){
            String pwd = MD5Util.encode(carUser.getPassword());
            System.out.println("登录密码" + pwd+"这是MD5加密过的");
            if(user.getPassword().equals(pwd)){
                String token = JwtUtil.generateToken(carUser.getPassword(), carUser.getEmail());
                CookieUtil.create(httpServletResponse, user.getId()+"", token, false, -1, "localhost");
                return user;
            }
        }else{
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        return null;
    }

    @Override
    public Boolean checkCokie(HttpServletRequest request ,String name) {
        CookieUtil.getValue(request, name);
        return null;
    }
}
