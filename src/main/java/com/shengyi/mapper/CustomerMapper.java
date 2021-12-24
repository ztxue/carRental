package com.shengyi.mapper;

import com.shengyi.model.CarCustomer;
import com.shengyi.model.vo.OptionRequest;
import com.shengyi.model.dto.CustomerDto;
import com.shengyi.model.dto.CustomerSexCountDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("SELECT id, customer_name, number, phone, lease_num, sex, age, address, create_time FROM car_customer")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "customerName", column = "customer_name"),
            @Result(property = "number", column = "number"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "leaseNum", column = "lease_num"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "age", column = "age")
    })
    List<CustomerDto> findAll();

    @Insert("INSERT INTO car_customer(customer_name, number, phone, sex, age, address) " +
            "VALUES (#{vo.customerName}, #{vo.number}, #{vo.phone}," +
            " #{vo.sex}, #{vo.age}, #{vo.address})")
    Integer insertCustomer(@Param("vo") CarCustomer carCustomer);

    @Update("UPDATE car_customer SET customer_name = #{vo.customerName}, number=#{vo.number}," +
            " phone=#{vo.phone}, sex=#{vo.sex}, age=#{vo.age}, address=#{vo.address} " +
            "WHERE id=#{vo.id}")
    Integer updateCustomer(@Param("vo") CarCustomer carCustomer);

    @Delete("DELETE FROM car_customer WHERE id = #{id}")
    Integer deleteCustomer(@Param("id") Long id);

    @Select("SELECT id, customer_name, number, phone, lease_num, sex, age, address, create_time " +
            "FROM car_customer " +
            "WHERE customer_name LIKE CONCAT('%',#{keywords},'%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "customerName", column = "customer_name"),
            @Result(property = "number", column = "number"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "leaseNum", column = "lease_num"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "age", column = "age")
    })
    List<CustomerDto> searchCustomerByName(@Param("keywords") String keywords);

    @Select("SELECT age FROM car_customer")
    List<Integer> findAllAge();

    @Select("SELECT sex,count(*)count FROM car_customer GROUP BY sex")
    @Results({
            @Result(property = "sex", column = "sex"),
            @Result(property = "count", column = "count")
    })
    List<CustomerSexCountDto> countSex();

    @Select("SELECT id, customer_name, number, phone, lease_num, sex, age, address FROM car_customer" +
            " WHERE id = #{customerId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "customerName", column = "customer_name"),
            @Result(property = "number", column = "number"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "leaseNum", column = "lease_num"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "age", column = "age")
    })
    CarCustomer findCustomerById(@Param("customerId") Long customerId);

    @Select("SELECT id value,CONCAT(customer_name,' ',number) label FROM car_customer WHERE customer_name LIKE CONCAT('%',#{number},'%')")
    List<OptionRequest> findOptionsByName(@Param("number") String number);

    @Update("UPDATE car_customer SET lease_num = (lease_num+1) WHERE id = #{id}")
    Integer updateLeaseNumById(@Param("id") Long id);
}
