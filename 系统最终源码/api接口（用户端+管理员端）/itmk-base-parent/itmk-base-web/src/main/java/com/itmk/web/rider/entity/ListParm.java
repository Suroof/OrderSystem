package com.itmk.web.rider.entity;


import lombok.Data;

/**
 * 骑手查询参数
 */
@Data
public class ListParm {
    private Integer currentPage; //当前页
    private Integer pageSize;    //每页查询的条数
    private String name;         //根据骑手姓名进行查询
}