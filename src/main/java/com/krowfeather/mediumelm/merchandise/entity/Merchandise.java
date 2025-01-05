package com.krowfeather.mediumelm.merchandise.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.krowfeather.mediumelm.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("merchandise")
public class Merchandise extends BaseEntity {
    private String name;
    private Double cost;
    private String menuId;
    private Integer soldout;
    private Double rate;
    private String icon;
}
