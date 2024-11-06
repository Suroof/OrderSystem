package com.itmk.web.wx_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.wx_user.entity.WxUser;

/**
 * 自由如風
 */
public interface WxUserService extends IService<WxUser> {
    int saveOrUpdateInfo(WxUser user);
}
