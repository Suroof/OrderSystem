package com.itmk.web.category.entity;

import lombok.Data;

/**
 * 自由如風 分页查询列表 参数  实体类
 */
@Data
public class ListParm {
    private Integer currentPage; //当前页
    private Integer pageSize;//每页查询的条数
    private String categoryName; //分类名称
}
