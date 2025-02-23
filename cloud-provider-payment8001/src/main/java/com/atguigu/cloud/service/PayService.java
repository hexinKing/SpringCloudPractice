package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

public interface PayService {

    int add(Pay pay);

    int update(Pay pay);

    int delete(Integer id);

    Pay getById(Integer id);

    List<Pay> getAll();

}
