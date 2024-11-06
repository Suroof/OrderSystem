package com.itmk.web.rider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.rider.entity.SysRider;
import org.springframework.stereotype.Service;

/**
 * 骑手服务接口
 */
@Service
public interface RiderService extends IService<SysRider> {
    //保存骑手信息
    void saveRider(SysRider rider);

    //编辑骑手信息
    void editRider(SysRider rider);

    //删除骑手信息
    void deleteRider(Integer id);
}