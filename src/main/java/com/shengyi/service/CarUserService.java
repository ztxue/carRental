package com.shengyi.service;

import com.shengyi.common.result.PageResult;
import com.shengyi.model.vo.UserSearchRequest;
import com.shengyi.model.vo.UserSysRequest;
import com.shengyi.model.dto.UserDto;

public interface CarUserService {
    PageResult<UserDto> getUserData(UserSysRequest request);

    PageResult<UserDto> searchUser(UserSearchRequest userSearchRequest);
}
