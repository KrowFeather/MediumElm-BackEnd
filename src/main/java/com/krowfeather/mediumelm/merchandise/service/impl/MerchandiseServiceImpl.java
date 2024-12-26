package com.krowfeather.mediumelm.merchandise.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import com.krowfeather.mediumelm.merchandise.mapper.MerchandiseMapper;
import com.krowfeather.mediumelm.merchandise.service.MerchandiseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseServiceImpl extends ServiceImpl<MerchandiseMapper, Merchandise> implements MerchandiseService {
    private final MerchandiseMapper merchaniseMapper;

    public MerchandiseServiceImpl(MerchandiseMapper merchaniseMapper) {
        this.merchaniseMapper = merchaniseMapper;
    }

    @Override
    public List<Merchandise> getMerchandisesByMid(String id) {
        return this.merchaniseMapper.getMerchandisesByMid(id);
    }
}
