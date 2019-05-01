package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
@Secured({"ROLE_ADMIN","ROLE_USER"})
public class ProductController {
    @Autowired
    ProductCService productCService;

    @RequestMapping("/FindAll_2")
    public ModelAndView findAll(){

       List<Product> productList=productCService.findAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productList",productList);

        modelAndView.setViewName("product-list");

        return modelAndView;
    }

    /**
     * 使用pagebean进行分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/FindAll_1")
    public ModelAndView fidAll_1( @RequestParam(name="pageNum",required = false,defaultValue = "1") Integer pageNum ,
                                  @RequestParam(name="pageSize",required = false,defaultValue = "3") Integer pageSize){
      PageBean<Product> pageBean= productCService.findByPage(pageNum,pageSize);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageBean",pageBean);

        modelAndView.setViewName("product-list");

        return modelAndView;
    }

    @RequestMapping("/findAll")
    public ModelAndView findByPage( @RequestParam(name="pageNum",required = false,defaultValue = "1") Integer pageNum ,
                                  @RequestParam(name="pageSize",required = false,defaultValue = "3") Integer pageSize){
       PageInfo<Product> productPageInfo=productCService.FindByPageheper(pageNum,pageSize);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PageInfo",productPageInfo);

        modelAndView.setViewName("product-list");

        return modelAndView;
    }


    @RequestMapping("/save")
    public String save(Product product){
        productCService.Save(product);

        return "redirect:/product/findAll";
    }

    @RequestMapping("/updateUI")
    public ModelAndView updateUI(Integer id){
        Product product=productCService.updateUI(id);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("product",product);

        modelAndView.setViewName("product-update");

        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Product product){
        productCService.update(product);

        return "redirect:/product/findAll";
    }

    @RequestMapping("/delOne")
    public String delOne(Integer id){
        productCService.delOne(id);

        return "redirect:/product/findAll";
    }

    @RequestMapping("/delMany")
    public String delMany(Integer[] ids){
        productCService.delMany(ids);

        return "redirect:/product/findAll";
    }
}
