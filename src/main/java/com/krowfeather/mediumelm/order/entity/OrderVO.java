package com.krowfeather.mediumelm.order.entity;

import com.krowfeather.mediumelm.merchant.enitity.Merchant;
import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private String id;
    private Integer uid;
    private Merchant merchant;
    private String address;
    private String detail;
    private String realName;
    private Integer status;
    private Double total;
    private List<SaleVO> items;
}
