package com.shengyi.common.result;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;
@SuppressWarnings("unchecked")
@Data
public class PageResult<E> {
    /**
     * 总条数
     */
    private Long total;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页数
     */
    private int pageNum;

    /**
     * 当前分页大小
     */
    private int pageSize;
    /**
     * 数据
     */
    private List<E> dataList;
    public PageResult(){

    }

    public PageResult<E> page(PageInfo page){
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.totalPage = page.getPages();
        this.total = page.getTotal();
        this.dataList = page.getList();
        return this;
    }
}
