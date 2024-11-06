package com.itmk.web.address.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 自由如風 新增地址实体类
 */
@Data
@TableName("user_address")
public class UserAddress {
    @TableId(type = IdType.AUTO)
    private Long addressId;
    private String openid;
    private String userName;
    private String phone;
    private String area;
    private String address;
    private String status;
}
