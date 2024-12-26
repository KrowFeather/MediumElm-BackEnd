package com.krowfeather.mediumelm.merchandise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krowfeather.mediumelm.merchandise.entity.Merchandise;

import java.util.List;

public interface MerchandiseService extends IService<Merchandise> {
    List<Merchandise> getMerchandisesByMid(String id);
}
