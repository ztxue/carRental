package com.shengyi.service;

import com.shengyi.common.Result;
import com.shengyi.common.result.PageResult;
import com.shengyi.model.CarCustomer;
import com.shengyi.model.vo.CustomerRequest;
import com.shengyi.model.vo.CustomerSearchRequest;
import com.shengyi.model.dto.CustomerDto;
import com.shengyi.model.dto.CustomerSexCountDto;

import java.util.List;

public interface CustomerService {
    PageResult<CustomerDto> getAllCustomer(CustomerRequest request);

    Result<Boolean> addCustomer(CarCustomer carCustomer);

    Result<Boolean> updateCustomer(CarCustomer carCustomer);

    Result<Boolean> deleteCustomer(Long id);

    PageResult<CustomerDto> searchCustomerByName(CustomerSearchRequest request);

    Result<List<Integer>> getAgeCount();

    Result<List<CustomerSexCountDto>> getSexCount();
}
