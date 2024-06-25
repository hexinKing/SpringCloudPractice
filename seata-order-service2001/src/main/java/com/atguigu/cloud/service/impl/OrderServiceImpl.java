package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entity.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;

    @GlobalTransactional(name = "zzyy-create-order", rollbackFor = Exception.class) //AT
    //@GlobalTransactional @Transactional(rollbackFor = Exception.class) //XA
    @Override
    public void create(Order order) {
        //xid检查
        String xid = RootContext.getXID();
        log.info("--------------------开始创建订单,XID为:{}", xid);
        //订单状态status：0：创建中；1：已完结
        order.setStatus(0);
        int insert = orderMapper.insert(order);
        if (insert > 0) {//插入成功
            Order orderFromDB = new Order();
            orderFromDB = orderMapper.selectById(order.getId());
            log.info("-------> 新建订单成功，orderFromDB info: " + orderFromDB);
            System.out.println();
            //2. 扣减库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            //3. 扣减账号余额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            //4. 修改订单状态
            //订单状态status：0：创建中；1：已完结
            order.setStatus(1);
            insert = orderMapper.updateById(order);
            log.info("-------> 修改订单状态完成" + "\t" + insert);
            log.info("-------> orderFromDB info: " + orderFromDB);
        }
        System.out.println();
        xid = RootContext.getXID();
        log.info("--------------------结束新建订单,XID为:{}", xid);

    }
}
