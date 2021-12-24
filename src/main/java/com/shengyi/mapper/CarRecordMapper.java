package com.shengyi.mapper;

import com.shengyi.mapper.provider.CarRecordProvider;
import com.shengyi.model.CarCustomer;
import com.shengyi.model.CarData;
import com.shengyi.model.CarRecord;
import com.shengyi.model.vo.CarRecordRequest;
import com.shengyi.model.dto.CarRecordDto;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarRecordMapper {
    @SelectProvider(type = CarRecordProvider.class,method = "searchRecord")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "carId",column = "car_id"),
            @Result(property = "timeLong",column = "time_long"),
            @Result(property = "deposit",column = "deposit"),
            @Result(property = "status",column = "status"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "customer",column = "customerId",javaType = CarCustomer.class,
                    one = @One(select = "com.shengyi.mapper.CustomerMapper.findCustomerById")),
            @Result(property = "carData",column = "carId",
                    javaType = CarData.class,one = @One(select = "com.shengyi.mapper.CarDataMapper.findCarDataById")),
            @Result(property = "carUser",column = "userId",
                    javaType = CarData.class,one = @One(select = "com.shengyi.mapper.CarUserMapper.findCarUserById"))
    })
    List<CarRecordDto> findRecordByTime(@Param("vo")CarRecordRequest recordRequest);

    @Delete("DELETE FROM car_record WHERE id = #{id}")
    Integer deleteRecordById(@Param("id") Long id);

    /**
     * @Options 返回主键 自增ID
     * @param carRecord
     * @return
     */
    @Insert("INSERT INTO car_record(customer_Id,user_Id,car_Id,time_Long,deposit,status,create_Time,update_Time) " +
            "VALUES(#{record.customerId},#{record.userId},#{record.carId},#{record.timeLong},#{record.deposit},#{record.status},now(),now())")
    @Options(useGeneratedKeys=true, keyProperty="record.id", keyColumn="id")
    Integer insertRecord(@Param("record")CarRecord carRecord);

    @Select("SELECT CURDATE()")
    Date selectSysDate();

    @Update("UPDATE car_record SET status = 1 WHERE id = #{id}")
    Integer updateStatusById(@Param("id") Long id);

}
