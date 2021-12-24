package com.shengyi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shengyi.common.BasicCode;
import com.shengyi.common.Result;
import com.shengyi.common.exception.ServiceException;
import com.shengyi.common.result.PageResult;

import com.shengyi.mapper.CustomerMapper;
import com.shengyi.model.CarCustomer;
import com.shengyi.model.vo.CustomerRequest;
import com.shengyi.model.vo.CustomerSearchRequest;
import com.shengyi.model.dto.CustomerDto;
import com.shengyi.model.dto.CustomerSexCountDto;
import com.shengyi.service.CustomerService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public PageResult<CustomerDto> getAllCustomer(CustomerRequest request) {
        if (request == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageNum() <=0 || request.getPageSize() <=0){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<CustomerDto> list = customerMapper.findAll();
        Page<CustomerDto> page = (Page<CustomerDto>) list;
        PageResult<CustomerDto> result = new PageResult<>();
        return setPage(page,result);
    }

    @Override
    public Result<Boolean> addCustomer(CarCustomer carCustomer) {
        if (carCustomer == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = customerMapper.insertCustomer(carCustomer);
        if (count<1){
            throw new ServiceException(BasicCode.DB_INSERT_FAILED);
        }
        Result<Boolean> result = new Result<>();
        return result.success(true);
    }

    @Override
    public Result<Boolean> updateCustomer(CarCustomer carCustomer) {
        if (carCustomer == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        Integer count = customerMapper.updateCustomer(carCustomer);
        if (count<1){
            throw new ServiceException(BasicCode.DB_UPDATE_FAILED);
        }
        Result<Boolean> result = new Result<>();
        return result.success(true);
    }

    @Override
    public Result<Boolean> deleteCustomer(Long id) {
        if (id == null || id<1){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        Integer count = customerMapper.deleteCustomer(id);
        if (count <1){
            throw new ServiceException(BasicCode.DB_DELETE_FAILED);
        }
        Result<Boolean> result = new Result<>();
        return result.success(true);
    }

    @Override
    public PageResult<CustomerDto> searchCustomerByName(CustomerSearchRequest request) {
        if (request == null){
            throw new ServiceException(BasicCode.PARAM_NULL);
        }
        if (request.getPageNum() <0 || request.getPageSize()<0){
            throw new ServiceException(BasicCode.INVALID_PARAM);
        }
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<CustomerDto> list = customerMapper.searchCustomerByName(request.getKeywords());
        Page<CustomerDto> page = (Page<CustomerDto>) list;
        PageResult<CustomerDto> result = new PageResult<>();
        return setPage(page,result);
    }

    @Override
    public Result<List<Integer>> getAgeCount() {
        List<Integer> list = customerMapper.findAllAge();
        Integer a = new Integer(0);
        Integer b = new Integer(0);
        Integer c = new Integer(0);
        Integer d = new Integer(0);
        Integer e = new Integer(0);
        Integer f = new Integer(0);
        Integer g = new Integer(0);
        Integer h = new Integer(0);
        for (Integer i : list){
            if (i <= 20){
                a++;
            }else if (i>20 && i<=25){
                b++;
            }else if (i>25 && i<=30){
                c++;
            }else if (i>30 && i<=35){
                d++;
            }else if (i>35 && i<=40){
                e++;
            }else if (i>40 && i<=45){
                f++;
            }else if (i>45 && i<=50){
                g++;
            }else{
                h++;
            }
        }
        List<Integer> countList = new ArrayList<>();
        countList.add(a);
        countList.add(b);
        countList.add(c);
        countList.add(d);
        countList.add(e);
        countList.add(f);
        countList.add(g);
        countList.add(h);
        Result<List<Integer>> result = new Result<>();
        return result.success(countList);
    }

    @Override
    public Result<List<CustomerSexCountDto>> getSexCount() {
        List<CustomerSexCountDto> list = customerMapper.countSex();
        Result<List<CustomerSexCountDto>> result = new Result<>();
        return result.success(list);
    }

    private PageResult<CustomerDto> setPage(Page page, PageResult<CustomerDto> pageResult){
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
