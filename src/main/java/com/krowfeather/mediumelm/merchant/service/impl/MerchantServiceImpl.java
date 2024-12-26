package com.krowfeather.mediumelm.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.krowfeather.mediumelm.merchant.enitity.Merchant;
import com.krowfeather.mediumelm.merchant.mapper.MerchantMapper;
import com.krowfeather.mediumelm.merchant.service.MerchantService;
import com.krowfeather.mediumelm.tag.entity.Tag;
import com.krowfeather.mediumelm.tag.mapper.TagMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {
    private final MerchantMapper merchantMapper;
    private final TagMapper tagMapper;
    public MerchantServiceImpl(MerchantMapper merchantMapper, TagMapper tagMapper) {
        this.merchantMapper = merchantMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public PageInfo<Merchant> getMerchantsPage(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Merchant> list = this.merchantMapper.selectList(new QueryWrapper<>());
        PageInfo<Merchant> pageInfo = new PageInfo<Merchant>(list);
        return pageInfo;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initBuiltinMerchant(){
        if(merchantMapper.selectCount(null)>0){
            return;
        }
        Merchant merchant = new Merchant();
        merchant.setName("鸦羽优选超市");
        merchant.setDeliveryFee(0.0);
        merchant.setDeliveryStart(10);
        merchantMapper.insert(merchant);
        Merchant merchant1 = new Merchant();
        merchant1.setName("沪上阿姨");
        merchant1.setDeliveryFee(1.0);
        merchant1.setDeliveryStart(15);
        merchantMapper.insert(merchant1);
        Merchant merchant2 = new Merchant();
        merchant2.setName("大象超市");
        merchant2.setDeliveryFee(2.0);
        merchant2.setDeliveryStart(15);
        merchantMapper.insert(merchant2);
        Tag tag = new Tag();
        tag.setCid(1);
        tag.setVid(merchant.getId());
        Tag tag1 = new Tag();
        tag1.setCid(2);
        tag1.setVid(merchant1.getId());
        Tag tag2 = new Tag();
        tag2.setCid(2);
        tag2.setVid(merchant2.getId());
        this.tagMapper.insert(tag);
        this.tagMapper.insert(tag1);
        this.tagMapper.insert(tag2);
    }

    @Override
    public List<Merchant> getMerchantsByTag(Integer tagId) {
        return this.merchantMapper.getMerchantsByTag(tagId);
    }
}
