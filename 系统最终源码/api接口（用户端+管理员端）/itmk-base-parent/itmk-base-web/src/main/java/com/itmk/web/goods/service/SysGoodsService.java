package com.itmk.web.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.goods.entity.GoodsParm;
import com.itmk.web.goods.entity.SysGoods;

/**
 * 自由如風
 */
public interface SysGoodsService extends IService<SysGoods> {
    //保存    //定义保存菜品的方法  类似用户点了一份什么规格的菜品
    void saveGoods(GoodsParm parm);
    //编辑
    void editGoods(GoodsParm parm);
    //删除
    void deleteGoods(Long goodsId);
}
