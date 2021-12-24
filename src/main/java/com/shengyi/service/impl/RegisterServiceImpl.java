package com.shengyi.service.impl;

import com.shengyi.common.BasicCode;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.common.util.MD5Util;
import com.shengyi.mapper.CarUserMapper;
import com.shengyi.model.CarUser;
import com.shengyi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private CarUserMapper carUserMapper;


    @Override
    public Boolean addUser(CarUser carUser) {
        System.out.println("carUser"+carUser);
        if(carUser == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        carUser.setPassword(MD5Util.encode(carUser.getPassword()));
        System.out.println(carUser);
        Integer count = carUserMapper.insertCarUser(carUser);
        if (count < 0){
            throw new ServiceException(BasicCode.DB_INSERT_FAILED);
        }
        return true;
    }
}
