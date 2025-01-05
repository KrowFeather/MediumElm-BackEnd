package com.krowfeather.mediumelm.merchant.enitity;

import com.krowfeather.mediumelm.activity.entity.Activity;

import java.util.List;

public class MerchantPO2VO {
    public static MerchantVO convert(Merchant merchant, List<Activity> activities) {
        MerchantVO merchantVO = new MerchantVO();
        merchantVO.setId(merchant.getId());
        merchantVO.setName(merchant.getName());
        merchantVO.setDeliveryFee(merchant.getDeliveryFee());
        merchantVO.setDeliveryStart(merchant.getDeliveryStart());
        merchantVO.setRate(merchant.getRate());
        merchantVO.setDistance(merchant.getDistance());
        merchantVO.setIcon(merchant.getIcon());
        merchantVO.setBanner(merchant.getBanner());
        merchantVO.setActivities(activities);
        return merchantVO;
    }
}
