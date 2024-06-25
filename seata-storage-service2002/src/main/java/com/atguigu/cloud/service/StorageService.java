package com.atguigu.cloud.service;

import com.atguigu.cloud.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hexin
 * @since 2024-06-24
 */
public interface StorageService extends IService<Storage> {

    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
