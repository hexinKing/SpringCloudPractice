package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer/pay")
public class OrderController {

    public static final String PaymentSrv_URL = "http://cloud-payment-service";//设置请求地址,服务注册中心上的微服务名称
    @Resource
    private RestTemplate restTemplate;

    /**
     * * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * * 参数可以不添加@RequestBody
     *
     * @param payDTO
     * @return
     */
    @GetMapping("/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/get/{id}")
    public ResultData getOrder(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/get")
    public ResultData getAllOrder() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get", ResultData.class);
    }

    @GetMapping("/del/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/del/" + id, id);
        return ResultData.success();
    }

    @GetMapping("/update")
    public ResultData updateOrder(PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
        return ResultData.success();
    }

    //    测试
    @GetMapping(value = "/get/info")
    private String getInfoByConsul() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }


}
