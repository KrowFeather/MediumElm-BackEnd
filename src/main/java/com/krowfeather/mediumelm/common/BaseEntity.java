package com.krowfeather.mediumelm.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    protected String id;
}
