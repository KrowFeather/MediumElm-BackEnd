package com.krowfeather.mediumelm.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("activity_registration")
public class ActivityRegistration {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer aid;
    private String mid;
}
