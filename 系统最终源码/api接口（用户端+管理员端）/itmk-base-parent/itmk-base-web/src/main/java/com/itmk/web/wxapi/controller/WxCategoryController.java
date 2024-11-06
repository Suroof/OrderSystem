package com.itmk.web.wxapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.category.entity.SysCategory;
import com.itmk.web.category.service.SysCategoryService;
import com.itmk.web.goods.entity.SysGoods;
import com.itmk.web.goods.service.SysGoodsService;
import com.itmk.web.goods_specs.entity.SysGoodsSpecs;
import com.itmk.web.goods_specs.service.SysGoodsSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 自由如風: 自由如风
 * @create: 2023-07-05
 * 小程序分类接口对接
 */
@RestController
@RequestMapping("/wxapi/category")
public class WxCategoryController {
    @Autowired
    private SysGoodsService sysGoodsService;
    @Autowired
    private SysCategoryService sysCategoryService;

    @Autowired
    private SysGoodsSpecsService sysGoodsSpecsService;

    //分类数据
    @GetMapping("/getCategoryList")
    public ResultVo getCategoryList(){
        //创建 条件构造器
        QueryWrapper<SysCategory> query = new QueryWrapper<>();
        //根据  OrderNum 字段 升序排序
        query.lambda().orderByAsc(SysCategory::getOrderNum);
        //查询出符合条件的数据 （所有数据）
        List<SysCategory> list = sysCategoryService.list(query);
        //长度大于0，说明有数据返回
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                //查询分类下面的菜品  根据分类id进行查询
                QueryWrapper<SysGoods> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysGoods::getCategoryId,list.get(i).getCategoryId())
                        .orderByAsc(SysGoods::getOrderNum);
                //查询出符合条件的数据
                List<SysGoods> goods = sysGoodsService.list(queryWrapper);
                //保存数据到 分类对象中
                list.get(i).setGoods(goods);
                //进一步判断  根据商品id查询
                if(goods.size() > 0){
                    for (int j = 0; j < goods.size(); j++) {
                        //查询价格
                        QueryWrapper<SysGoodsSpecs> queryWrapperPrice = new QueryWrapper<>();
                        queryWrapperPrice.lambda().eq(SysGoodsSpecs::getGoodsId,goods.get(j).getGoodsId())
                                .orderByAsc(SysGoodsSpecs::getOrderNum);
                        //查询出符合条件的数据
                        List<SysGoodsSpecs> specs = sysGoodsSpecsService.list(queryWrapperPrice);
                        //保存数据到 规格对象中
                        goods.get(j).setSpecs(specs);
                    }
                }
            }
        }
        //返回 数据给到前端即可，前端 进一步处理
        return ResultUtils.success("查询成功",list);
    }
}
