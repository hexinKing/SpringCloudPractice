package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/pay")
@Slf4j
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/add")
    public ResultData addOrder(PayDTO payDTO) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/get/{id}")
    public ResultData getOrder(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            System.out.println("调用开始-----:" + DateUtil.now());
            resultData = payFeignApi.getBYIdPay(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:" + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    @GetMapping("/get")
    public ResultData getAllOrder() {
        System.out.println("-------支付微服务远程调用，查询所有订单支付流水信息");
        return payFeignApi.getAllPay();
    }

    @GetMapping("/del/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id删除订单支付流水信息");
        return payFeignApi.deletePay(id);
    }

    @GetMapping("/update")
    public ResultData updateOrder(PayDTO payDTO) {
        System.out.println("-------支付微服务远程调用，按照id修改订单支付流水信息");
        return payFeignApi.updatePay(payDTO);
    }

    //    测试
    @GetMapping(value = "/get/info")
    private String getInfoByConsul() {
        System.out.println("-------支付微服务远程调用，测试负载均衡");
        return payFeignApi.mylb();
    }


}
