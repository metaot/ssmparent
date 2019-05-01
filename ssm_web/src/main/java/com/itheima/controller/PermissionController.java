package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/permission")
@Controller
@Secured({"ROLE_ADMIN"})
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize",required = false,defaultValue = "3") Integer pageSize){
        PageInfo<Permission> PageInfo=permissionService.findByPage(pageNum,pageSize);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PageInfo",PageInfo);

        modelAndView.setViewName("permission-list");

        return modelAndView;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
       List<Permission> permissionList= permissionService.findParentPermission();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("permissionList",permissionList);

        modelAndView.setViewName("permission-add");

        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Permission permission){

        permissionService.save(permission);

        return "redirect:/permission/findAll";
    }
}
