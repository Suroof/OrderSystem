package com.itmk.web.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.category.entity.SysCategory;
import com.itmk.web.category.mapper.SysCategoryMapper;
import com.itmk.web.category.service.SysCategoryService;
import org.springframework.stereotype.Service;

/**
 * 自由如風  菜品分类 业务层接口实现类
 */
@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategory> implements SysCategoryService {
}
