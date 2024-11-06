package com.itmk.config;

import com.itmk.web.user.entity.SysUser;

public class LoginResponse {
    private boolean success;
    private String message;
    private SysUser user;
    private String token;

    public LoginResponse(boolean success, String message, SysUser user, String token) {
        this.success = success;
        this.message = message;
        this.user = user;
        this.token = token;
    }

    // getters and setters
}
