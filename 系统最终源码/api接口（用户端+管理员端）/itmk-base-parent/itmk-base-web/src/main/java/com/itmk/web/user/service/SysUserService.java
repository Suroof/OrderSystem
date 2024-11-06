package com.itmk.web.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.user.entity.SysUser;


/**
 * 自由如風 用户管理的业务逻辑层
 */
public interface SysUserService extends IService<SysUser> {
    public SysUser login(String username, String password);

}
