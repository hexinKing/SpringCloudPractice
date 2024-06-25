package com.atguigu.cloud.service;

import com.atguigu.cloud.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hexin
 * @since 2024-06-24
 */
public interface AccountService extends IService<Account> {

    /**
     * 扣减账户余额
     */
    void decrease(Long userId, Long money);
}
