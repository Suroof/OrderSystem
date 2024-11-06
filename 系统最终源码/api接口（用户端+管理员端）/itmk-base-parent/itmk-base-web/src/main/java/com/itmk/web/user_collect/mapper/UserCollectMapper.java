package com.itmk.web.user_collect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmk.web.user_collect.entity.UserCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自由如風
 */
public interface UserCollectMapper extends BaseMapper<UserCollect> {

    //获取收藏列表数据
    List<UserCollect> getList(@Param("openid") String openid);
}
