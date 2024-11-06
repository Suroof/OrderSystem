package com.itmk.web.address.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.address.entity.UserAddress;
import com.itmk.web.address.mapper.UserAddressMapper;
import com.itmk.web.address.service.UserAddressService;
import org.springframework.stereotype.Service;

/**
 * 自由如風
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {
}
