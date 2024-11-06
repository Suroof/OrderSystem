package com.itmk.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 自由如風
 */
@Data
public class OrderParm {
    //主表需要的数据
    private String openid;
    private String userName;
    private String phone;
    private String address;
    private BigDecimal price;
    //相当于订单对应的商品有哪些，通过list集合来接收，存储到数组里面（子表需要的数据 ）
    private List<ParmDetail> details = new ArrayList<>();
}
