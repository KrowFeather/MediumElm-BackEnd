package com.krowfeather.mediumelm.merchant.enitity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.krowfeather.mediumelm.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@TableName("merchant")
@EqualsAndHashCode(callSuper = false)
@Data
public class Merchant extends BaseEntity {
    private String name;
    private Integer deliveryStart;
    private Double deliveryFee;
    private Double rate;
    private Double distance;
    private Integer soldout;
    private String icon;
    private String banner;
}
