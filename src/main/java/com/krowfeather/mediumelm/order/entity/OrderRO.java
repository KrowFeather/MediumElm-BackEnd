package com.krowfeather.mediumelm.order.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderRO {
    private Integer uid;
    private String mid;
    private String address;
    private String detail;
    private String realName;
    private Double total;
    private List<Sale> items;
}
