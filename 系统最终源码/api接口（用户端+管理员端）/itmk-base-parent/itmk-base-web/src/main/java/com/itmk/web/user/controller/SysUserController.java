package com.itmk.web.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.config.LoginRequest;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.user.entity.SysUser;
import com.itmk.web.user.entity.UserPageParm;
import com.itmk.web.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理的控制器，请求转发
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysUser user) {
        //判断账户是否重复
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, user.getUsername()).eq(SysUser::getPassword, user.getPassword());
        //根据用户名和密码查询   一条数据
        SysUser one = sysUserService.getOne(query);
        //如果返回的对象one  的数据不为空，说明查询到了这个人，即用户已存在
        if (one != null) {
            return ResultUtils.error("用户账户和密码重复!");
        }

        if (sysUserService.save(user)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysUser user) {
        if (sysUserService.updateById(user)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId) {
        if (sysUserService.removeById(userId)) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(UserPageParm parm) {
        //构造分页
        IPage<SysUser> page = new Page<>(parm.getCurrentPage(), parm.getPageSize());
        //构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getName()), SysUser::getName, parm.getName())
                .like(StringUtils.isNotEmpty(parm.getPhone()), SysUser::getPhone, parm.getPhone())
                .orderByAsc(SysUser::getName);
        IPage<SysUser> list = sysUserService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    @Autowired
    private SysUserService userService;

    //登录
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        SysUser user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            // 这里可以返回 JWT token 或用户信息
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    // 退出登录
    @PostMapping("/logout")
    public ResultVo logout(HttpServletRequest request) {
        try {
            // 从请求头获取token
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            // 这里可以添加清除token的逻辑
            return ResultUtils.success("退出成功!");
        } catch (Exception e) {
            return ResultUtils.error("退出失败!");
        }
    }
}
