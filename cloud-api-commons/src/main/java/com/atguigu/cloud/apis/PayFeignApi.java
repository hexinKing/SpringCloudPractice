package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("cloud-gateway")
public interface PayFeignApi {

    /**
     * 添加
     *
     * @param payDTO
     * @return
     */
    @PostMapping(value = "/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 删除
     */
    @DeleteMapping("/pay/del/{id}")
    ResultData deletePay(@PathVariable("id") Integer id);

    /**
     * 修改
     */
    @PutMapping("/pay/update")
    ResultData updatePay(@RequestBody PayDTO payDTO);

    /**
     * 根据id查询
     */
    @GetMapping("/pay/get/{id}")
    ResultData getBYIdPay(@PathVariable("id") Integer id);

    /**
     * 查询所有数据
     */
    @GetMapping("/pay/get")
    ResultData getAllPay();

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    String mylb();

    /**
     * Resilience4j CircuitBreaker 的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     *
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();


}
