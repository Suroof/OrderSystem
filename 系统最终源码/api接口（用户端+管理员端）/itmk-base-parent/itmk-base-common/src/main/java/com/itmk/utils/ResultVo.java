package com.itmk.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自由如風: 自由如风
 * @create: 2023-06-28
 */
@Data  //生成get、set方法
@AllArgsConstructor  //生成所有带参数的构造方法，一个参数的方法，两个等等。。。
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;
}
