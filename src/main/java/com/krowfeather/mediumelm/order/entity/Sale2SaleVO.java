package com.krowfeather.mediumelm.order.entity;

public class Sale2SaleVO {
    public static SaleVO convert(Sale sale) {
        SaleVO saleVO = new SaleVO();
        saleVO.setId(sale.getId());
        saleVO.setOid(sale.getOid());
        saleVO.setQuantity(sale.getQuantity());
        return saleVO;
    }
}
