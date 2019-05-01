package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderDao {
    @Select("select * from orders")
    @Results({
            @Result(column = "productId",property = "product",javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findById",fetchType = FetchType.LAZY))
    })
    List<Order> findAll();

    @SelectKey(keyColumn ="id",keyProperty = "id",before = true,resultType =Long.class ,statement = "select order_seq.nextval from dual")
    @Insert("insert into orders values(#{id},#{orderNum},#{orderTime},#{peopleCount},#{orderDesc}, #{payType}, #{orderStatus}, #{product.id})")
    void save(Order order);

    @Select("delete from orders where id=#{id}")
    void delById(Integer id);
}
