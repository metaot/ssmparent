package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.SysTUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
@Secured({"ROLE_ADMIN"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required =false,defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize",required = false,defaultValue = "3") Integer pageSize){
        PageInfo<SysTUser> PageInfo=userService.findByPageHelper(pageNum,pageSize);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PageInfo",PageInfo);

        modelAndView.setViewName("user-list");

        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(SysTUser user){
        userService.save(user);

        return "redirect:/user/findAll";
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public String checkName(String username){

        boolean flag=userService.checkName(username);

        return flag+"";
    }

    @RequestMapping("/details")
    public ModelAndView details(Integer userId){
       SysTUser user= userService.findByUserId(userId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user",user);

        modelAndView.setViewName("user-show");

        return modelAndView;
    }

    @RequestMapping("/addRoleListToUserUI")
    public ModelAndView addRoleListToUserUI(Integer userId){
        List<Role> roleList=roleService.findAll();

        SysTUser user = userService.findByUserId(userId);

        List<Role> userWithRoleList = user.getRoleList();

        StringBuilder builder = new StringBuilder();

        for (Role role : userWithRoleList) {
            builder.append(",");

            builder.append(role.getId());

            builder.append(",");
        }

        Integer id = user.getId();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("roleList",roleList);

        modelAndView.addObject("userWithRoleListStr",builder.toString());

        modelAndView.addObject("userId",id);

        modelAndView.setViewName("user-role-add");

        return modelAndView;
    }

    @RequestMapping("/addRoleListToUser")
    public String addRoleListToUser(Integer[] ids,Integer userId){
        userService.addRoleListToUser(ids,userId);

        return "redirect:/user/findAll";
    }
}
