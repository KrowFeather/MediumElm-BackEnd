package com.krowfeather.mediumelm.order.entity;

import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import lombok.Data;

@Data
public class SaleVO {
    private Integer id;
    private String oid;
    private Merchandise merchandise;
    private Integer quantity;
}
