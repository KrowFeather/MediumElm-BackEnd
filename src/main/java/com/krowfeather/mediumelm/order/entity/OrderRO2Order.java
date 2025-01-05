package com.krowfeather.mediumelm.order.entity;

public class OrderRO2Order {
    public static Order convert(OrderRO orderRO) {
        Order order = new Order();
        order.setUid(orderRO.getUid());
        order.setMid(orderRO.getMid());
        order.setAddress(orderRO.getAddress());
        order.setDetail(orderRO.getDetail());
        order.setRealName(orderRO.getRealName());
        order.setTotal(orderRO.getTotal());
        return order;
    }
}
