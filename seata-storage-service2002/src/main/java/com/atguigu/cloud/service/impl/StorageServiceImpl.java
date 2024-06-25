package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entity.Storage;
import com.atguigu.cloud.mapper.StorageMapper;
import com.atguigu.cloud.service.StorageService;
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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
//        1.查询productId对应的表数据，判断剩余库存是否满足>=count
//        方法一
//        Map<String, Object> map = new HashMap<>();
//        map.put("productId",productId);
//        List<StorageDTO> storages = storageMapper.selectByMap(map);
//        方法二
        LambdaQueryWrapper<Storage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Storage::getProductId, productId);
        Storage storage = storageMapper.selectOne(queryWrapper);
        log.info("查询的库存信息为:", storage);
        if (storage.getResidue() >= count) {//库存满足>=count
            log.info("开始扣减库存——————————————");
//        2.若剩余库存不为空，则更新剩余库存-count，并将count加入已用库存
            Storage sto = new Storage();
            sto.setId(storage.getId());
            sto.setResidue(storage.getResidue() - count);
            sto.setUsed(storage.getUsed() + count);
            int i = storageMapper.updateById(sto);
            log.info("结束扣减库存———————————更新数据信息条数为：", i);
        } else {
            throw new RuntimeException("剩余库存不足以商品扣减");
        }

    }
}
