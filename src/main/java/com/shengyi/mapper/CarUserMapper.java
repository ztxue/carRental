package com.shengyi.mapper;

import com.shengyi.model.CarUser;
import com.shengyi.model.vo.OptionRequest;
import com.shengyi.model.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarUserMapper{
    @Select("SELECT id,user_name,password,email,phone,sex,job_num FROM car_user WHERE email=#{email}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "jobNum",column = "job_num")
    })
    CarUser findUserByEmail(@Param("email") String email);

    @Insert("INSERT INTO car_user ( user_name,password,email,phone,sex,job_num )" +
            " VALUES( #{user.userName}, #{user.password}, #{user.email}, #{user.phone}, #{user.sex}, #{user.jobNum} )")
    Integer insertCarUser(@Param("user") CarUser carUser);

    @Select("SELECT id,user_name,email,phone,sex,job_num FROM car_user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "jobNum",column = "job_num")
    })
    List<UserDto> findAll();

    @Select("SELECT id,user_name,email,phone,sex,job_num FROM car_user WHERE user_name LIKE CONCAT('%',#{userName},'%')")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "jobNum",column = "job_num")
    })
    List<UserDto> searchUser(@Param("userName") String userName);

    @Select("SELECT id,user_name FROM car_user")
    @Results({
            @Result(property = "value",column = "id"),
            @Result(property = "label",column = "user_name")
    })
    List<OptionRequest> findOptions();

    @Select("SELECT id,user_name,password,email,phone,sex,job_num FROM car_user WHERE id=#{userId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "jobNum",column = "job_num")
    })
    CarUser findCarUserById(@Param("userId")Long userId);
}
