package com.itmk.web.goods.entity;

import lombok.Data;

/**
 * 自由如風
 */
@Data
public class ListParm {
    private Integer currentPage; //当前页
    private Integer pageSize;//每页查询的条数
    private String goodsName; //根据菜品名称进行 查询
}
