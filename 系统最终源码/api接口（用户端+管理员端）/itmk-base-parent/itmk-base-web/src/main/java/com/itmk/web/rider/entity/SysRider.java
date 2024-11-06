package com.itmk.web.rider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 骑手实体类
 */
@Data
@TableName("rider_management")
public class SysRider {
    @TableId(type = IdType.AUTO)
    private Integer riderId;

    private String name;

    private Integer age;

    private String vehicle;

    private Integer contribution;

    private Integer status;

    private Integer phone;

    private String email;
}