package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;
    //   consul分布式配置测试
    @Value("${server.port}")
    private String port;

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info("{}", pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<String> deletePay(@PathVariable("id") Integer id) {
        log.info("{}", id);
        int i = payService.delete(id);
        return ResultData.success("成功删除记录，返回值：" + i);
    }

    @PutMapping("/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        log.info("{}", pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData<Pay> getBYIdPay(@PathVariable("id") Integer id) {
//        故意写暂停程序60秒
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}", id);
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/get")
    @Operation(summary = "查询所有数据", description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAllPay() {
        log.info("查询所有数据");
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }

    @GetMapping(value = "/get/info")
    private String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo) {
        return "atguiguInfo: " + atguiguInfo + "\t" + "port: " + port;
    }


}
