package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCServiceImpl implements ProductCService {
    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void Save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product updateUI(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delOne(Integer id) {
        productDao.delOne(id);
    }

    @Override
    public void delMany(Integer[] ids) {
        if(ids!=null){
            for (Integer id : ids) {
                productDao.delOne(id);
            }
        }
    }

    @Override
    public PageBean<Product> findByPage(Integer pageNum, Integer pageSize) {
        PageBean<Product> pageBean = new PageBean<>();

        pageBean.setPageNum(pageNum);

        pageBean.setPageSize(pageSize);

        Integer totalCount=productDao.findTotalCount();

        pageBean.setTotalCount(totalCount);

        pageBean.setTotalPage((int)Math.ceil(totalCount*1.0/pageSize));

        Integer startRowNum=pageNum*pageSize-(pageSize-1);

        Integer endRowNum=pageNum*pageSize;

        pageBean.setData(productDao.findByPage(startRowNum,endRowNum));

        return pageBean;
    }

    @Override
    public void findByPagehelper(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productDao.findAll();

//        System.out.println(productList.size());
        PageInfo<Product> productPageInfo = new PageInfo<>(productList,3);

        System.out.println("总页数:"+productPageInfo.getPages());

        System.out.println("总条数:"+productPageInfo.getTotal());

        System.out.println("当前页:"+productPageInfo.getPageNum());

        System.out.println("每页条数:"+productPageInfo.getPageSize());

        System.out.println("当前页数据:"+productPageInfo.getList().size());

        System.out.println("是否是第一页:"+productPageInfo.isIsFirstPage());

        System.out.println("是否是最后一页:"+productPageInfo.isIsLastPage());

        System.out.println("上一页:"+productPageInfo.getPrePage());

        System.out.println("下一页:"+productPageInfo.getNextPage());

        System.out.println("页面上要显示的第一个页码:"+productPageInfo.getNavigateFirstPage());

        System.out.println("页面上要显示的最后一个页码:"+productPageInfo.getNavigateLastPage());
    }

    @Override
    public PageInfo<Product> FindByPageheper(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productDao.findAll();

        PageInfo<Product> productPageInfo = new PageInfo<>(productList,4);

        return productPageInfo;
    }

}
