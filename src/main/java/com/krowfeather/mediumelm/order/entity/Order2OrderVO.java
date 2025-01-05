package com.krowfeather.mediumelm.order.entity;

public class Order2OrderVO {
    public static OrderVO convert(Order order) {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(order.getId());
        orderVO.setUid(order.getUid());
        orderVO.setAddress(order.getAddress());
        orderVO.setDetail(order.getDetail());
        orderVO.setRealName(order.getRealName());
        orderVO.setStatus(order.getStatus());
        orderVO.setTotal(order.getTotal());
        return orderVO;
    }
}
