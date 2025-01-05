package com.krowfeather.mediumelm.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krowfeather.mediumelm.order.entity.Order;
import com.krowfeather.mediumelm.order.entity.OrderRO;
import com.krowfeather.mediumelm.order.entity.OrderVO;

import java.util.List;

public interface OrderService extends IService<Order> {
    String create(OrderRO order);
    List<OrderVO> getAll();
    List<OrderVO> getOrdersByStatus(String type);
    String updateByIdPay(Order order, String username);
}
