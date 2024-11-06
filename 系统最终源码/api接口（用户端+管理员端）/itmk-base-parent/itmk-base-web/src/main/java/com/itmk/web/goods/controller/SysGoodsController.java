package com.itmk.web.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.category.entity.SelectType;
import com.itmk.web.goods.entity.GoodsParm;
import com.itmk.web.goods.entity.ListParm;
import com.itmk.web.goods.entity.SysGoods;
import com.itmk.web.goods.service.SysGoodsService;
import com.itmk.web.goods_specs.entity.SysGoodsSpecs;
import com.itmk.web.goods_specs.service.SysGoodsSpecsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自由如風
 */
@RestController
@RequestMapping("/api/goods")
public class SysGoodsController {
    @Autowired
    private SysGoodsService sysGoodsService;
    @Autowired
    private SysGoodsSpecsService sysGoodsSpecsService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody GoodsParm parm){
        sysGoodsService.saveGoods(parm);
        return ResultUtils.success("新增成功!");
    }

    //列表分页查询   根据菜品名称进行 分页查询
    @GetMapping("/list")
    public ResultVo getList(ListParm parm){
        //构造分页对象
        IPage<SysGoods> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<SysGoods> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getGoodsName()),SysGoods::getGoodsName,parm.getGoodsName())
                .orderByDesc(SysGoods::getOrderNum);
        //查询到符合条件的数据
        IPage<SysGoods> list = sysGoodsService.page(page, query);
        //判断  查询的记录数 长度大于0
        if(list.getRecords().size() > 0){
            //遍历数据
            for (int i = 0; i < list.getRecords().size(); i++) {
            QueryWrapper<SysGoodsSpecs> queryWrapper = new QueryWrapper<>();
            //查询条件 根据goods_id字段查询sys_goods_specs表数据，并且根据order_num字段进行 升序排序
            queryWrapper.lambda().eq(SysGoodsSpecs::getGoodsId,list.getRecords().get(i).getGoodsId())
                    .orderByAsc(SysGoodsSpecs::getOrderNum);
                //查询到集合数据
                List<SysGoodsSpecs> specs = sysGoodsSpecsService.list(queryWrapper);
                //获取每一条数据（即菜品规格表的每一条数据），然后 返回前端数据 规格对象 ，前端设置 属性（字段）的值
                list.getRecords().get(i).setSpecs(specs);
            }
        }
        //返回 数据给到前端即可，前端 进一步处理
        return ResultUtils.success("查询成功",list);
    }



    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody GoodsParm parm){
        sysGoodsService.editGoods(parm);
        return ResultUtils.success("编辑成功!");
    }
    //删除
    @DeleteMapping("/{goodsId}")
    public ResultVo delete(@PathVariable("goodsId") Long goodsId){
        sysGoodsService.deleteGoods(goodsId);
        return ResultUtils.success("删除成功!");
    }



    //列表   下拉选择器  实体类 新增功能里面菜品有下拉列表，需要查询有多少菜品
    @GetMapping("/getSelectList")
    public ResultVo getSelectList(){
        List<SysGoods> list = sysGoodsService.list();
        //组装为前端下拉选择器需要的数据格式
        List<SelectType> selectList = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item ->{
                    SelectType type = new SelectType();
                    type.setLabel(item.getGoodsName());
                    type.setValue(item.getGoodsId());
                    selectList.add(type);
                });
        return ResultUtils.success("查询成功!",selectList);
    }


}
