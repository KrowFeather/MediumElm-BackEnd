package com.krowfeather.mediumelm.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sale")
public class Sale {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String oid;
    private String mid;
    private Integer quantity;
}
