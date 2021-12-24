package com.shengyi.controller;

import com.shengyi.common.result.PageResult;
import com.shengyi.model.vo.UserSearchRequest;
import com.shengyi.model.vo.UserSysRequest;
import com.shengyi.model.dto.UserDto;
import com.shengyi.service.CarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user/")
public class UserSysController {
    @Autowired
    private CarUserService carUserService;

    @ResponseBody
    @PostMapping(value = "getUserList")
    public PageResult<UserDto> getUserList(@RequestBody UserSysRequest request){
        return carUserService.getUserData(request);
    }

    @ResponseBody
    @PostMapping(value = "searchUserByUserName")
    public PageResult<UserDto> searchUserByUserName(@RequestBody UserSearchRequest request){
        return carUserService.searchUser(request);
    }
}
