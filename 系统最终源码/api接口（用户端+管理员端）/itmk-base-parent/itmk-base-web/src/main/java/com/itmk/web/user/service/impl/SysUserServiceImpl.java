package com.itmk.web.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.user.entity.SysUser;
import com.itmk.web.user.mapper.SysUserMapper;
import com.itmk.web.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自由如風
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser login(String username, String password) {
        SysUser user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // 你可以返回用户信息或 JWT token
        }
        return null;
    }
}

