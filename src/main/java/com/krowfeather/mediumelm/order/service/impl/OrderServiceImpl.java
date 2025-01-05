package com.krowfeather.mediumelm.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import com.krowfeather.mediumelm.merchandise.mapper.MerchandiseMapper;
import com.krowfeather.mediumelm.merchant.mapper.MerchantMapper;
import com.krowfeather.mediumelm.order.entity.*;
import com.krowfeather.mediumelm.order.mapper.OrderMapper;
import com.krowfeather.mediumelm.order.mapper.SaleMapper;
import com.krowfeather.mediumelm.order.service.OrderService;
import com.krowfeather.mediumelm.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;
    private final SaleMapper saleMapper;
    private final MerchantMapper merchantMapper;
    private final MerchandiseMapper merchandiseMapper;
    private final UserMapper userMapper;
    public OrderServiceImpl(OrderMapper orderMapper, SaleMapper saleMapper, MerchantMapper merchantMapper, MerchandiseMapper merchandiseMapper, UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.saleMapper = saleMapper;
        this.merchantMapper = merchantMapper;
        this.merchandiseMapper = merchandiseMapper;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public String create(OrderRO orderro) {
        System.out.println(orderro);
        Order order = OrderRO2Order.convert(orderro);
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        order.setCreateTime(new java.util.Date());
        orderMapper.insert(order);
        List<Sale> sales = orderro.getItems();
        for (Sale sale : sales) {
            sale.setOid(orderId);
            saleMapper.insert(sale);
        }
        return "ok";
    }

    @Transactional
    @Override
    public List<OrderVO> getAll() {
        List<Order> orders = orderMapper.selectList(null);
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = Order2OrderVO.convert(order);
            orderVO.setMerchant(merchantMapper.selectById(order.getMid()));
            List<Sale> sales = saleMapper.selectList(new QueryWrapper<Sale>().eq("oid", order.getId()));
            List<SaleVO> saleVOS = new ArrayList<>();
            for (Sale sale : sales) {
                SaleVO saleVO = Sale2SaleVO.convert(sale);
                saleVO.setMerchandise(merchandiseMapper.selectById(sale.getMid()));
                saleVOS.add(saleVO);
            }
            orderVO.setItems(saleVOS);
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Transactional
    @Override
    public List<OrderVO> getOrdersByStatus(String type) {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().eq("status", type));
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order order : orders) {
            OrderVO orderVO = Order2OrderVO.convert(order);
            orderVO.setMerchant(merchantMapper.selectById(order.getMid()));
            List<Sale> sales = saleMapper.selectList(new QueryWrapper<Sale>().eq("oid", order.getId()));
            List<SaleVO> saleVOS = new ArrayList<>();
            for (Sale sale : sales) {
                SaleVO saleVO = Sale2SaleVO.convert(sale);
                saleVO.setMerchandise(merchandiseMapper.selectById(sale.getMid()));
                saleVOS.add(saleVO);
            }
            orderVO.setItems(saleVOS);
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Transactional
    @Override
    public String updateByIdPay(Order order, String username) {
        this.orderMapper.updateById(order);
        this.userMapper.updateMoney(username, order.getTotal());
        List<Sale> sales = this.saleMapper.selectList(new QueryWrapper<Sale>().eq("oid", order.getId()));
        for (Sale sale : sales) {
            Merchandise merchandise = this.merchandiseMapper.selectById(sale.getMid());
            merchandise.setSoldout(merchandise.getSoldout() + sale.getQuantity());
            this.merchandiseMapper.update(merchandise, new QueryWrapper<Merchandise>().eq("id", sale.getMid()));
        }
        return "ok";
    }

}
