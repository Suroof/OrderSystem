package com.itmk.web.category.entity;

import lombok.Data;

/**
 * 自由如風 下拉选择器  实体类 新增功能里面菜品分类有下拉列表，需要查询商品分类
 */
@Data
public class SelectType {
    private Long value;
    private String label;
}