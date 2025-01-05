package com.krowfeather.mediumelm.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.order.entity.Order;
import com.krowfeather.mediumelm.order.entity.OrderRO;
import com.krowfeather.mediumelm.order.service.OrderService;
import com.krowfeather.mediumelm.user.entity.dto.User;
import com.krowfeather.mediumelm.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "订单管理接口", description = "用于管理订单信息的接口")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    /**
     * 创建新的订单
     *
     * @param order 订单信息
     * @return 返回创建后的订单信息
     */
    @Operation(
            summary = "创建新的订单",
            description = "根据传入的订单信息创建新的订单。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功创建订单"),
            @ApiResponse(responseCode = "400", description = "请求数据无效")
    })
    @PostMapping("")
    public Result createOrder(@RequestBody OrderRO order) {
        return Result.success(this.orderService.create(order));
    }

    /**
     * 获取所有订单信息
     *
     * @return 返回所有订单信息
     */
    @Operation(
            summary = "获取所有订单",
            description = "获取系统中所有的订单信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取订单信息"),
            @ApiResponse(responseCode = "404", description = "没有找到订单")
    })
    @GetMapping("")
    public Result getOrders() {
        return Result.success(this.orderService.getAll());
    }

    /**
     * 根据订单状态获取订单
     *
     * @param type 订单状态类型
     * @return 返回该状态下的所有订单
     */
    @Operation(
            summary = "根据订单状态获取订单",
            description = "根据订单的状态类型获取对应的订单信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取订单"),
            @ApiResponse(responseCode = "404", description = "没有找到订单")
    })
    @GetMapping("/status/{type}")
    public Result getOrdersByStatus(@PathVariable String type) {
        return Result.success(this.orderService.getOrdersByStatus(type));
    }

    /**
     * 更新订单状态
     *
     * @param oid 订单ID
     * @param order 更新后的订单信息
     * @return 返回更新后的订单信息
     */
    @Operation(
            summary = "更新订单状态",
            description = "根据订单ID更新指定订单的状态。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新订单状态"),
            @ApiResponse(responseCode = "404", description = "未找到该订单")
    })
    @PutMapping("/{oid}")
    public Result updateOrderStatus(@PathVariable String oid, @RequestBody Order order) {
        return Result.success(this.orderService.updateById(order));
    }

    /**
     * 更新订单支付状态
     *
     * @param oid 订单ID
     * @param username 用户名
     * @param order 更新后的订单信息
     * @return 返回支付后的订单信息
     */
    @Operation(
            summary = "更新订单支付状态",
            description = "根据订单ID和用户名更新订单支付状态，并验证用户余额是否足够。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新支付状态"),
            @ApiResponse(responseCode = "400", description = "余额不足，支付失败"),
            @ApiResponse(responseCode = "404", description = "未找到订单或用户信息")
    })
    @PutMapping("/{oid}/{username}")
    public Result updateOrderStatusPay(@PathVariable String oid, @PathVariable String username, @RequestBody Order order) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user.getMoney() < order.getTotal()) {
            return Result.error("余额不足");
        }
        return Result.success(this.orderService.updateByIdPay(order, username));
    }
}
