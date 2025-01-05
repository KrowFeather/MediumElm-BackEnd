package com.krowfeather.mediumelm.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.krowfeather.mediumelm.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("orders")
public class Order extends BaseEntity {
    private Integer uid;
    private String mid;
    private String address;
    private String detail;
    private String realName;
    private Double total;
    private Integer status;
    private Integer payType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
