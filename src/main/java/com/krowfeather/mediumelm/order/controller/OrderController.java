package com.krowfeather.mediumelm.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.order.entity.Order;
import com.krowfeather.mediumelm.order.entity.OrderRO;
import com.krowfeather.mediumelm.order.service.OrderService;
import com.krowfeather.mediumelm.user.entity.dto.User;
import com.krowfeather.mediumelm.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("")
    public Result createOrder(@RequestBody OrderRO order) {
        return Result.success(this.orderService.create(order));
    }

    @GetMapping("")
    public Result getOrders() {
        return Result.success(this.orderService.getAll());
    }

    @GetMapping("/status/{type}")
    public Result getOrdersByStatus(@PathVariable String type) {
        return Result.success(this.orderService.getOrdersByStatus(type));
    }

    @PutMapping("/{oid}")
    public Result updateOrderStatus(@PathVariable String oid, @RequestBody Order order) {
        return Result.success(this.orderService.updateById(order));
    }

    @PutMapping("/{oid}/{username}")
    public Result updateOrderStatusPay(@PathVariable String oid,@PathVariable String username, @RequestBody Order order) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username",username));
        if(user.getMoney()<order.getTotal())
            return Result.error("余额不足");
        return Result.success(this.orderService.updateByIdPay(order,username));
    }
}
