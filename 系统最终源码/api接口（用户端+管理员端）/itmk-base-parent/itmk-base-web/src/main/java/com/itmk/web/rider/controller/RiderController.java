package com.itmk.web.rider.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.rider.entity.ListParm;
import com.itmk.web.rider.entity.SysRider;
import com.itmk.web.rider.service.RiderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 骑手管理控制器
 */
@RestController
@RequestMapping("/api/rider")
public class RiderController {
    @Autowired
    private RiderService riderService;

    // 新增骑手
    @PostMapping
    public ResultVo add(@RequestBody SysRider rider) {
        riderService.saveRider(rider);
        return ResultUtils.success("新增成功!");
    }

    // 列表分页查询
    @GetMapping("/list")
    public ResultVo getList(ListParm parm) {
        // 构造分页对象
        IPage<SysRider> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        // 构造查询条件
        QueryWrapper<SysRider> query = new QueryWrapper<>();
        query.lambda()
                .like(StringUtils.isNotEmpty(parm.getName()), SysRider::getName, parm.getName())
                .orderByDesc(SysRider::getContribution);
        // 查询数据
        IPage<SysRider> list = riderService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    // 编辑骑手
    @PutMapping
    public ResultVo edit(@RequestBody SysRider rider) {
        riderService.editRider(rider);
        return ResultUtils.success("编辑成功!");
    }

    // 删除骑手
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") Integer id) {
        riderService.deleteRider(id);
        return ResultUtils.success("删除成功!");
    }

    // 添加一个测试接口
    @GetMapping("/test")
    public String test() {
        return "Rider controller is working!";
    }
}
