package com.krowfeather.mediumelm.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("activity")
public class Activity {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String description;
    private Integer priority;
}
