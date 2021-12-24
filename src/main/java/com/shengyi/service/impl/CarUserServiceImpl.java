package com.shengyi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengyi.common.BasicCode;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.common.result.PageResult;
import com.shengyi.mapper.CarUserMapper;
import com.shengyi.model.vo.UserSearchRequest;
import com.shengyi.model.vo.UserSysRequest;
import com.shengyi.model.dto.UserDto;
import com.shengyi.service.CarUserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@SuppressWarnings("unchecked")
@Service
public class CarUserServiceImpl implements CarUserService {
    @Resource
    private CarUserMapper carUserMapper;

    @Override
    public PageResult<UserDto> getUserData(UserSysRequest request) {
        if (request == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageNum() <= 0 || request.getPageSize() <=0){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<UserDto> list = carUserMapper.findAll();
        for (UserDto dto : list){
            if ("f".equals(dto.getSex())){
                dto.setSex("男");
            } else {
                dto.setSex("女");
            }
        }
        Page<UserDto> page = (Page<UserDto>) list;
        PageResult<UserDto> result = new PageResult<>();
        return setPage(page,result);
    }

    @Override
    public PageResult<UserDto> searchUser(UserSearchRequest userSearchRequest) {
        if(userSearchRequest == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (userSearchRequest.getPageNum() <= 0 || userSearchRequest.getPageSize() <=0){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(userSearchRequest.getPageNum(), userSearchRequest.getPageSize());
        List<UserDto> list = carUserMapper.searchUser(userSearchRequest.getUserName());
        for (UserDto dto : list) {
            if ("f".equals(dto.getSex())) {
                dto.setSex("男");
            } else {
                dto.setSex("女");
            }
        }
        Page<UserDto> page = (Page<UserDto>) list;
        PageResult<UserDto> result = new PageResult<>();
        return setPage(page,result);
    }

    private PageResult<UserDto> setPage(Page page,PageResult<UserDto> pageResult){
        if (page != null){
            pageResult.setTotalPage(page.getPages());
            pageResult.setTotal(page.getTotal());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setPageNum(page.getPageNum());
            pageResult.setDataList(page.getResult());
        }
        return pageResult;
    }
}
