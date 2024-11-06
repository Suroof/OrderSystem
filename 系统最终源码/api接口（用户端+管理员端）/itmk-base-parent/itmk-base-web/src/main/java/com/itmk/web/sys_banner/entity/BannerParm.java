package com.itmk.web.sys_banner.entity;

import lombok.Data;

/**
 * 自由如風 广告分页实体类参数
 */
@Data
public class BannerParm {
    private Integer currentPage; //当前页
    private Integer pageSize;//每页查询的条数
    private String title;
}
