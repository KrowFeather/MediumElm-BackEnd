package com.krowfeather.mediumelm.merchant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.krowfeather.mediumelm.merchant.enitity.Merchant;

import java.util.List;

public interface MerchantService extends IService<Merchant> {

    List<Merchant> getMerchantsByTag(Integer tagId);

    PageInfo<Merchant> getMerchantsPage(Integer pageNum, Integer pageSize);

}
