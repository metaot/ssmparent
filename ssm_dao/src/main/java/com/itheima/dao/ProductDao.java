package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {
    @Select("select * from product")
    List<Product> findAll();

    @Select("insert into product values(product_seq.nextval,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product where id = #{id}")
    Product findById(Integer id);

    @Select("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id}")
    void update(Product product);

    @Select("delete from product where id=#{id}")
    void delOne(Integer id);

    @Select("select count(id) from product")
    Integer findTotalCount();

    @Select("select * from  (select p.*,rownum rn from product p ) t where t.rn between #{param1} and #{param2}")
    List<Product> findByPage(Integer startRowNum, Integer endRowNum);
}
