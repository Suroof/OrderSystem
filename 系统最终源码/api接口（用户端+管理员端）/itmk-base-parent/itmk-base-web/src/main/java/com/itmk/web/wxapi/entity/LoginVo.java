package com.itmk.web.wxapi.entity;

import lombok.Data;

@Data
public class LoginVo {
    private String openid;
    private String sessionKey;
}
