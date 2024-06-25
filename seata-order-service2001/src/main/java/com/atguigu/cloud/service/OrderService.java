package com.atguigu.cloud.service;

import com.atguigu.cloud.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {

    void create(Order order);
}
