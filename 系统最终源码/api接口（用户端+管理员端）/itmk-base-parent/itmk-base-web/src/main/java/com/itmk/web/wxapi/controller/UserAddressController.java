package com.itmk.web.wxapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.address.entity.UserAddress;
import com.itmk.web.address.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 自由如風: 自由如风
 * @create: 2023-07-07
 */
@RestController
@RequestMapping("/wxapi/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    //新增地址    接收前端新增地址 表单 提交的这些值
    @PostMapping
    public ResultVo add(@RequestBody UserAddress userAddress){
        if(userAddressService.save(userAddress)){
            return ResultUtils.success("新增地址成功!");
        }
        return ResultUtils.error("新增地址失败!");
    }

    ////列表
    //@GetMapping("/list")
    //public ResultVo list(){
    //    //排序：按status排序，默认的排在第一条
    //    QueryWrapper<UserAddress> query = new QueryWrapper<>();
    //    query.lambda().orderByDesc(UserAddress::getStatus);
    //    List<UserAddress> list = userAddressService.list(query);
    //    return ResultUtils.success("查询成功",list);
    //}


    //列表   （修改后端查询列表的方法）
    @GetMapping("/list")
    public ResultVo list(String openid){
        //排序：按status排序，默认的排在第一条
        QueryWrapper<UserAddress> query = new QueryWrapper<>();
        //查询条件  openid
        query.lambda().eq(UserAddress::getOpenid,openid);
        query.lambda().orderByDesc(UserAddress::getStatus);
        List<UserAddress> list = userAddressService.list(query);
        return ResultUtils.success("查询成功",list);
    }

    //编辑地址
    // 只能有一个默认地址  ，修改时怎么做 ？先全部更新为不是默认，即state值都为0，更新当前编辑的这条数据，即state值都为1
    @PutMapping
    public ResultVo edit(@RequestBody UserAddress userAddress){
        //先全部更新为不是默认
        LambdaUpdateWrapper<UserAddress> query = new LambdaUpdateWrapper<>();
        query.eq(UserAddress::getOpenid,userAddress.getOpenid()).set(UserAddress::getStatus,"0");
        userAddressService.update(query);
        //更新当前编辑的这条数据
        if(userAddressService.updateById(userAddress)){
            return ResultUtils.success("编辑地址成功!");
        }
        return ResultUtils.error("编辑地址失败!");
    }


    //查询默认地址
    @GetMapping("/getAddress")
    public ResultVo getAddress(String openid){
        QueryWrapper<UserAddress> query = new QueryWrapper<>();
        query.lambda().eq(UserAddress::getStatus,"1")
                .eq(UserAddress::getOpenid,openid);
        UserAddress one = userAddressService.getOne(query);
        return ResultUtils.success("查询成功",one);
    }


}