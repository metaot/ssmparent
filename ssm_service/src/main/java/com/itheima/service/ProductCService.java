package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

import java.util.List;


public interface ProductCService {

    List<Product> findAll();

    void Save(Product product);

    Product updateUI(Integer id);

    void update(Product product);

    void delOne(Integer id);

    void delMany(Integer[] ids);

    PageBean<Product> findByPage(Integer pageNum, Integer pageSize);

    void findByPagehelper(Integer pageNum, Integer pageSize);

    PageInfo<Product> FindByPageheper(Integer pageNum, Integer pageSize);
}
