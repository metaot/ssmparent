package com.itheima;

import com.itheima.service.ProductCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")
public class TestService {
    @Autowired
    ProductCService productCService;

    @Test
    public void testFIndByPageHelper(){
        productCService.findByPagehelper(2,5);
    }
}
