package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/order")
@Secured({"ROLE_ADMIN","ROLE_USER"})
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductCService productCService;

    @RequestMapping("findAll")
    public ModelAndView findByPage(@RequestParam(name = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize",required = false,defaultValue = "3") Integer pageSize){
       PageInfo<Order> orderPageInfo= orderService.findByPage(pageNum,pageSize);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PageInfo",orderPageInfo);

        modelAndView.setViewName("order-list");

        return modelAndView;
    }

    @RequestMapping("/findAll_1")
    public ModelAndView findAll(){

        List<Order> orderList = orderService.findAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("orderList",orderList);

        modelAndView.setViewName("order-list");

        return  modelAndView;
    }


    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){

        List<Product> productList = productCService.findAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productList",productList);

        modelAndView.setViewName("order-add");

        return  modelAndView;
    }


    @RequestMapping("/save")
    public String save(Order order){

        orderService.save(order);

        return "redirect:/order/findAll";

    }

    @RequestMapping("/delOne")
    public String delOne(Integer id){
        orderService.delOne(id);

        return "redirect:/order/findAll";
    }

    @RequestMapping("/delMany")
    public String delMany(Integer[] ids){
        orderService.delMany(ids);

        System.out.println("6666666");

        return "redirect:/order/findAll";
    }
}
