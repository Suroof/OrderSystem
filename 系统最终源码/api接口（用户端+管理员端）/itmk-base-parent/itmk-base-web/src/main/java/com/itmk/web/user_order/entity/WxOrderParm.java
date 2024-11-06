package com.itmk.web.user_order.entity;

import lombok.Data;

/**
 * 自由如風 查询pc订单  订单列表查询
 */
@Data
public class WxOrderParm {
    private String openid;
    private String type;
    private Long currentPage;
    private Long pageSize;

    //根据用户名进行查询
    private String userName;
}
