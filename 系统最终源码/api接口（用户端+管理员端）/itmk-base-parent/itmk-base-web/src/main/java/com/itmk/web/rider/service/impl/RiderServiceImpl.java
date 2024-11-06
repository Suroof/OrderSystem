package com.itmk.web.rider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.rider.entity.SysRider;
import com.itmk.web.rider.mapper.RiderMapper;
import com.itmk.web.rider.service.RiderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 骑手服务实现类
 */
@Service
public class RiderServiceImpl extends ServiceImpl<RiderMapper, SysRider> implements RiderService {

    @Override
    @Transactional
    public void saveRider(SysRider rider) {
        try {
            this.baseMapper.insert(rider);
        } catch (Exception e) {
            throw new RuntimeException("保存骑手信息失败");
        }
    }

    @Override
    @Transactional
    public void editRider(SysRider rider) {
        try {
            this.baseMapper.updateById(rider);
        } catch (Exception e) {
            throw new RuntimeException("更新骑手信息失败");
        }
    }

    @Override
    @Transactional
    public void deleteRider(Integer id) {
        try {
            this.baseMapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("删除骑手信息失败");
        }
    }
}