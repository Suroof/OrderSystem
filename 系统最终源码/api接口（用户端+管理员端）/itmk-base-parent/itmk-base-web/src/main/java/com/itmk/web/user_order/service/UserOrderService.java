package com.itmk.web.user_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.user_order.entity.OrderParm;
import com.itmk.web.user_order.entity.SunList;
import com.itmk.web.user_order.entity.UserOrder;
import com.itmk.web.user_order.entity.WxOrderParm;

import java.util.List;

/**
 * 自由如風
 */
public interface UserOrderService extends IService<UserOrder> {

    //创建订单（下单，提交订单）
    void splaceOrder(OrderParm parm);

    //获取下单的商品有哪些
    IPage<UserOrder> getOrderList(WxOrderParm parm);

    //   查询pc订单  订单列表查询
    IPage<UserOrder> getPcOrderList(WxOrderParm parm);

    //按天查询
    List<SunList> getDays();
    //按月查询
    List<SunList> getMonths();
    //按年查询
    List<SunList> getYears();

}
