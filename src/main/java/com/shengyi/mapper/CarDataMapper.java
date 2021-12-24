package com.shengyi.mapper;

import com.shengyi.model.CarData;
import com.shengyi.model.vo.CarDataStatusRequest;
import com.shengyi.model.dto.CarDataDto;
import com.shengyi.model.dto.CarOptions;
import com.shengyi.model.dto.CarStockDetailsDto;
import com.shengyi.model.dto.CarStockDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarDataMapper {
    @Select("SELECT COUNT(*) stock,class_name,c.id class_id " +
            "FROM car_class c RIGHT JOIN " +
            "(SELECT * FROM car_data WHERE status=1) d " +
            "ON c.id = d.class_id " +
            "GROUP BY d.class_id")
    @Results({
            @Result(property = "className", column = "class_name"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "classId", column = "class_id"),
    })
    List<CarStockDto> findAllStock();

    @Select("SELECT number,brand,color,seating FROM car_data WHERE class_id = #{classId}")
    List<CarStockDetailsDto> findStockDetails(@Param("classId") Long classId);

    @Delete("DELETE FROM car_data WHERE class_id=#{classId}")
    Integer deleteCarDataByClassId(@Param("classId") Long classId);

    @Select("SELECT d.id,brand,number,color,seating,status,status_Dsecrbe,day_price,month_price," +
            "run_km,buy_date,class_id,user_id,c.class_name,u.user_name " +
            "FROM car_class c,car_user u,car_data d " +
            "WHERE d.user_id = u.id AND d.class_id = c.id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "brand", column = "brand"),
            @Result(property = "number", column = "number"),
            @Result(property = "color", column = "color"),
            @Result(property = "seating", column = "seating"),
            @Result(property = "status", column = "status"),
            @Result(property = "statusDescribe", column = "status_Dsecrbe"),
            @Result(property = "dayPrice", column = "day_price"),
            @Result(property = "monthPrice", column = "month_price"),
            @Result(property = "runKm", column = "run_km"),
            @Result(property = "buyDate", column = "buy_date"),
            @Result(property = "className", column = "class_name"),
            @Result(property = "classId", column = "class_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
    })
    List<CarDataDto> findAllData();

    @Delete("DELETE FROM car_data WHERE id = #{id}")
    Integer deleteCarDataById(@Param("id") Long id);

    @Update("UPDATE car_data SET brand=#{data.brand},number=#{data.number},color=#{data.color},seating=#{data.seating}," +
            "day_price=#{data.dayPrice},month_price=#{data.monthPrice},run_km=#{data.runKm}," +
            "class_id=#{data.classId},user_id=#{data.userId} " +
            "WHERE id = #{data.id}")
    Integer updateCarData(@Param("data") CarDataDto carDataDto);

    @Update("UPDATE car_data SET status=#{vo.status},status_Dsecrbe=#{vo.statusDescribe} WHERE id=#{vo.id}")
    Integer updateStatus(@Param("vo") CarDataStatusRequest request);

    @Insert("INSERT INTO " +
            "car_data(brand,number,color,seating,status,status_Dsecrbe," +
            "day_price,month_price,run_km,class_id,user_id,buy_date) " +
            "VALUES(" +
            "#{data.brand},#{data.number},#{data.color},#{data.seating},#{data.status}," +
            "#{data.statusDescribe},#{data.dayPrice},#{data.monthPrice}," +
            "#{data.runKm},#{data.classId},#{data.userId},#{data.buyDate})")
    Integer insertCarData(@Param("data")CarDataDto carDataDto);

    @Select("SELECT d.id,brand,number,color,seating,status,status_Dsecrbe,day_price,month_price," +
            "run_km,buy_date,class_id,user_id,c.class_name,u.user_name " +
            "FROM car_class c,car_user u,car_data d " +
            "WHERE d.user_id = u.id AND d.class_id = c.id AND brand LIKE CONCAT('%',#{brand},'%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "brand", column = "brand"),
            @Result(property = "number", column = "number"),
            @Result(property = "color", column = "color"),
            @Result(property = "seating", column = "seating"),
            @Result(property = "status", column = "status"),
            @Result(property = "statusDescribe", column = "status_Dsecrbe"),
            @Result(property = "dayPrice", column = "day_price"),
            @Result(property = "monthPrice", column = "month_price"),
            @Result(property = "runKm", column = "run_km"),
            @Result(property = "buyDate", column = "buy_date"),
            @Result(property = "className", column = "class_name"),
            @Result(property = "classId", column = "class_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
    })
    List<CarDataDto> findCarDataByBrand(@Param("brand") String brand);

    @Select("SELECT id,brand,number,color,seating,day_price,month_price," +
            "run_km,buy_date " +
            "FROM car_data " +
            "WHERE id=#{carId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "brand", column = "brand"),
            @Result(property = "number", column = "number"),
            @Result(property = "color", column = "color"),
            @Result(property = "seating", column = "seating"),
            @Result(property = "dayPrice", column = "day_price"),
            @Result(property = "monthPrice", column = "month_price"),
            @Result(property = "runKm", column = "run_km"),
    })
    CarData findCarDataById(@Param("carId") Long carId);

    @Select("SELECT id value,CONCAT(brand,' ',number) label FROM car_data WHERE class_id = #{classId}")
    List<CarOptions> findCarOptionsByClassId(@Param("classId") Long classId);

    @Update("UPDATE car_data SET status = #{status}, status_Dsecrbe = #{statusDescribe} WHERE id=#{id}")
    Integer updateStatusById(@Param("status") Boolean status, @Param("statusDescribe") String statusDescribe, @Param("id") Long id);
}
