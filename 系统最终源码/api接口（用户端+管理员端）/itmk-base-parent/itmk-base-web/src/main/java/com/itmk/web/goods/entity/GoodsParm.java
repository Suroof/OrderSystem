package com.itmk.web.goods.entity;


import com.itmk.web.goods_specs.entity.SysGoodsSpecs;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 自由如風  用户下单菜品，接收的参数
 */
@Data
public class GoodsParm {
    private Long goodsId;
    private Long categoryId;
    private String goodsName;
    private String goodsImage;
    private String goodsDesc;
    private String status;
    private String goodsUnit;
    private String orderNum;

    private List<SysGoodsSpecs> specs = new ArrayList<>();
}