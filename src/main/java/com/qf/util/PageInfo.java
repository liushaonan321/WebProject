package com.qf.util;

import lombok.Data;

import java.util.List;

/**
 * @author 刘少楠
 * @Date 2019/05/26
 */
@Data
public class PageInfo<page> {
//    当前页
    private Integer page;

    private Integer size;
//    总条数
    private Integer count;
//    总页数
    private Integer pages;

    private Integer offset;

    private Integer end;

    private List<page> list;


    public PageInfo(Integer page, Integer size, Integer count) {
        this.page = page;
        this.size = size;
        this.count = count;
        this.pages = (int)(Math.ceil(count * 1.0/size));
        this.offset= (1+(page-1)*size);
    }
}
