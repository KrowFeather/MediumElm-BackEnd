package com.krowfeather.mediumelm.categories.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("categories")
public class Categories {
    @TableId(type = IdType.AUTO)
    private int id;
    private String title;
}
