package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entity.Account;
import com.atguigu.cloud.mapper.AccountMapper;
import com.atguigu.cloud.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hexin
 * @since 2024-06-24
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, Long money) {
//        根据userId查询当前账户的剩余余额,判断余额是否>=money
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUserId, userId);
        Account account = accountMapper.selectOne(queryWrapper);
        if (account.getResidue() >= money) {
            log.info("开始扣减账户余额——————————————");
//        是则对当前账户进行更新residue-money ,used+money
            Account acc = new Account();
            acc.setId(account.getId());
            acc.setResidue(account.getResidue() - money);
            acc.setUsed(account.getUsed() + money);
            int i = accountMapper.updateById(acc);
            log.info("扣减账户余额结束—————更新字段条数为：", i);
        } else {
            throw new RuntimeException("余额不足");
        }

    }

}
