package com.krowfeather.mediumelm.merchant.enitity;

import com.krowfeather.mediumelm.activity.entity.Activity;
import lombok.Data;

import java.util.List;

@Data
public class MerchantVO {
    private String id;
    private String name;
    private Integer deliveryStart;
    private Double deliveryFee;
    private Double rate;
    private Double distance;
    private Integer soldout;
    private String icon;
    private String banner;
    private List<Activity> activities;
}
