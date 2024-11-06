package com.itmk.web.wxapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.goods.entity.SysGoods;
import com.itmk.web.goods.service.SysGoodsService;
import com.itmk.web.goods_specs.entity.SysGoodsSpecs;
import com.itmk.web.goods_specs.service.SysGoodsSpecsService;
import com.itmk.web.sys_banner.entity.SysBanner;
import com.itmk.web.sys_banner.service.SysBannerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页轮播图接口对接
 *
 * 首页热推商品接口对接
 * 查询商品  商品信息包括详情，详情是在另外一张表中，需要注入两个service
 */
@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private SysBannerService sysBannerService;
    @Autowired
    private SysGoodsService sysGoodsService;
    @Autowired
    private SysGoodsSpecsService sysGoodsSpecsService;

    ////首页轮播图
    //@GetMapping("/getSwipperList")
    //public ResultVo getSwipperList(){
    //    QueryWrapper<SysBanner> query = new QueryWrapper<>();
    //    //查询条件 是 状态值为1的数据，即上架商品图片，不是下架的
    //    query.lambda().eq(SysBanner::getStatus,"1");
    //    //查询出符合条件的菜品
    //    List<SysBanner> list = sysBannerService.list(query);
    //    //返回集合对象 到前端
    //    return ResultUtils.success("查询成功",list);
    //}

    //首页轮播图  广告表（轮播图表）
    @GetMapping("/getSwipperList")
    public ResultVo getSwipperList(){
        //创建条件构造器对象
        QueryWrapper<SysBanner> query = new QueryWrapper<>();
        //构建查询条件 是 状态值为1的数据，即上架商品图片，不是下架的
        query.lambda().eq(SysBanner::getStatus,"1");
        //查询出符合条件的菜品
        List<SysBanner> list = sysBannerService.list(query);
        //进一步 判断
        if(list.size() > 0){
            for (int i=0;i<list.size();i++){
                if(StringUtils.isNotEmpty(list.get(i).getGoodsId().toString())){
                    //查询商品
                    SysGoods goods = sysGoodsService.getById(list.get(i).getGoodsId());
                    list.get(i).setSysGoods(goods);
                    //查询价格
                QueryWrapper<SysGoodsSpecs> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysGoodsSpecs::getGoodsId,list.get(i).getGoodsId())
                        .orderByAsc(SysGoodsSpecs::getOrderNum);
                List<SysGoodsSpecs> specs = sysGoodsSpecsService.list(queryWrapper);
                list.get(i).getSysGoods().setSpecs(specs);
                }
            }
        }
        //返回集合对象 到前端
        return ResultUtils.success("查询成功",list);
    }

    //首页热推  商品表
    @GetMapping("/getHotList")
    public ResultVo getHotList(){
        //创建条件构造器对象
        QueryWrapper<SysGoods> query = new QueryWrapper<>();
        //构建查询条件 是 状态值为1的数据，即上架商品图片，不是下架的   升序 排序一下
        query.lambda().eq(SysGoods::getStatus,"1")
                .orderByAsc(SysGoods::getOrderNum);
        //查询出符合条件的菜品
        List<SysGoods> list = sysGoodsService.list(query);
        //判断  如果大于0  有数据
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                //查询价格
                QueryWrapper<SysGoodsSpecs> queryWrapper = new QueryWrapper<>();
                //构建条件  菜品id  序号升序排序
                queryWrapper.lambda().eq(SysGoodsSpecs::getGoodsId,list.get(i).getGoodsId())
                        .orderByAsc(SysGoodsSpecs::getOrderNum);
                //查询  符合条件的数据
                List<SysGoodsSpecs> specs = sysGoodsSpecsService.list(queryWrapper);
                //从specs对象 中获取到价格
                list.get(i).setSpecs(specs);
            }
        }
        //返回集合对象 到前端
        return ResultUtils.success("查询成功",list);
    }
}
