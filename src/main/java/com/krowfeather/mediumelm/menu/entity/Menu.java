package com.krowfeather.mediumelm.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.krowfeather.mediumelm.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("menu")
public class Menu extends BaseEntity {
    private String title;
    private String mid;
}
