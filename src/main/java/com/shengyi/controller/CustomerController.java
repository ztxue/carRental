package com.shengyi.controller;

import com.shengyi.common.Result;
import com.shengyi.common.result.PageResult;
import com.shengyi.model.CarCustomer;
import com.shengyi.model.vo.CustomerRequest;
import com.shengyi.model.vo.CustomerSearchRequest;
import com.shengyi.model.dto.CustomerDto;
import com.shengyi.model.dto.CustomerSexCountDto;
import com.shengyi.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "getAllCustomer")
    @ResponseBody
    public PageResult<CustomerDto> getAllCustomer(@RequestBody CustomerRequest request){
        return customerService.getAllCustomer(request);
    }

    @PostMapping(value = "addCustomer")
    @ResponseBody
    public Result<Boolean> addCustomer(@RequestBody CarCustomer carCustomer){
        return customerService.addCustomer(carCustomer);
    }

    @PostMapping(value = "modifyCustomer")
    @ResponseBody
    public Result<Boolean> modifyCustomer(@RequestBody CarCustomer carCustomer){
        return customerService.updateCustomer(carCustomer);
    }

    @PostMapping(value = "removeCustomer")
    @ResponseBody
    public Result<Boolean> removeCustomer(@Param("id") Long id){
        return customerService.deleteCustomer(id);
    }

    @PostMapping(value = "searchCustomer")
    @ResponseBody
    public PageResult<CustomerDto> searchCustomer(@RequestBody CustomerSearchRequest request){
        return customerService.searchCustomerByName(request);
    }

    @PostMapping(value = "getAgeCount")
    @ResponseBody
    public Result<List<Integer>> getAgeCount(){
        return customerService.getAgeCount();
    }

    @PostMapping(value = "getSexCount")
    @ResponseBody
    public Result<List<CustomerSexCountDto>> getSexCount(){
        return customerService.getSexCount();
    }
}
