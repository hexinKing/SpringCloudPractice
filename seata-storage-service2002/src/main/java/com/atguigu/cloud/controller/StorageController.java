package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zzyy
 * @create 2023-12-01 18:09
 */

@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        log.info("传过来的数据：{},{}", productId, count);
        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功!");
    }
}
