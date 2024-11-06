package com.itmk.web.category.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.category.entity.ListParm;
import com.itmk.web.category.entity.SelectType;
import com.itmk.web.category.entity.SysCategory;
import com.itmk.web.category.service.SysCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自由如風 菜品分类控制层
 */
@RestController
@RequestMapping("/api/category")
public class SysCategoryController {
    @Autowired
    private SysCategoryService sysCategoryService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysCategory sysCategory){
        if(sysCategoryService.save(sysCategory)){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysCategory sysCategory){
        if(sysCategoryService.updateById(sysCategory)){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{categoryId}")
    public ResultVo delete(@PathVariable("categoryId") Long categoryId){
        if(sysCategoryService.removeById(categoryId)){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表  分页列表查询
    @GetMapping("/list")
    public ResultVo list(ListParm listParm){
        //构造分页对象
        IPage<SysCategory> page = new Page<>(listParm.getCurrentPage(),listParm.getPageSize());
        //构造查询条件
        QueryWrapper<SysCategory> query = new QueryWrapper<>();
        //三个参数：菜品分类名称不为空，要查询的参数（字段）是菜品分类名称，菜品分类名称的值是什么  根据order_num字段顺序排序
        query.lambda().like(StringUtils.isNotEmpty(listParm.getCategoryName()),SysCategory::getCategoryName,listParm.getCategoryName())
                .orderByAsc(SysCategory::getOrderNum);
        //分页查询成功
        IPage<SysCategory> list = sysCategoryService.page(page, query);
         //返回分页查询的数据
         return ResultUtils.success("查询成功!",list);
    }

    //列表  下拉选择器  实体类 新增功能里面菜品分类有下拉列表，需要查询商品分类
    @GetMapping("/getSelectList")
    public ResultVo getSelectList(){
        List<SysCategory> list = sysCategoryService.list();
        //组装为前端下拉选择器需要的数据格式
        List<SelectType> selectList = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item ->{
                    SelectType type = new SelectType();
                    type.setLabel(item.getCategoryName());
                    type.setValue(item.getCategoryId());
                    selectList.add(type);
                });
        return ResultUtils.success("查询成功!",selectList);
    }

}
