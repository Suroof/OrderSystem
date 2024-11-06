package com.itmk.web.wx_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.wx_user.entity.WxUser;
import org.apache.ibatis.annotations.Param;

/**
 * 自由如風
 */
public interface WxUserMapper extends BaseMapper<WxUser> {
    int saveOrUpdateInfo(@Param("user") WxUser user);
}
