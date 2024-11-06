package com.itmk.web.user.entity;

import lombok.Data;

/**
 * 自由如風 分页参数实体类
 */
@Data
public class UserPageParm {
    private Integer currentPage; //当前页
    private Integer pageSize;//每页查询的条数
    private String name;
    private String phone;
}

