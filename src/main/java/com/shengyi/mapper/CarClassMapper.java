package com.shengyi.mapper;

import com.shengyi.model.CarClass;
import com.shengyi.model.vo.OptionRequest;
import com.shengyi.model.dto.ClassOptions;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarClassMapper {
    @Select("SELECT id,class_name FROM car_class")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "className",column = "class_name")
    })
    List<CarClass> findAllClass();

    @Insert("INSERT INTO car_class(class_name) VALUES(#{className})")
    Integer insertClass(@Param("className") String className);

    @Update("UPDATE car_class SET class_name = #{vo.className} WHERE id=#{vo.id}")
    Integer updateClassNameById(@Param("vo") CarClass carClass);

    @Delete("DELETE FROM car_class WHERE id = #{classId}")
    Integer deleteClassById(@Param("classId") Long classId);

    @Select("SELECT id,class_name FROM car_class")
    @Results({
            @Result(property = "value",column = "id"),
            @Result(property = "label",column = "class_name")
    })
    List<OptionRequest> findOptions();

    @Select("SELECT id classId,class_name FROM car_class")
    @Results({
            @Result(property = "value",column = "classId"),
            @Result(property = "label",column = "class_name"),
            @Result(property = "children",column = "classId",javaType = List.class,
            one = @One(select = "com.shengyi.mapper.CarDataMapper.findCarOptionsByClassId"))
    })
    List<ClassOptions> findRecordOptions();
}
