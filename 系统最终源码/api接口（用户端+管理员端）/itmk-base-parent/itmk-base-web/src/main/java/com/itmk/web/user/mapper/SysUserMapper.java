package com.itmk.web.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.user.entity.SysUser;

/**
 * 自由如風: 自由如风
 * @create: 2023-06-28
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findByUsername(String username);
}
