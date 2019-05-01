package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    void save(Order order);

    void delOne(Integer id);

    void delMany(Integer[] ids);

    PageInfo<Order> findByPage(Integer pageNum, Integer pageSize);
}
