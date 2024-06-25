package com.atguigu.cloud.controller;

import com.atguigu.cloud.entity.Order;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@Tag(name = "订单控制器")
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @Operation(summary = "下订单")
    @GetMapping("/create")
    public ResultData create(Order order) {
        log.info("", order);
        orderService.create(order);
        return ResultData.success(order);
    }

}
