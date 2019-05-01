package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
@Secured({"ROLE_ADMIN"})
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSizee",required = false,defaultValue = "3") Integer pageSize){
       PageInfo<Role> PageInfo= roleService.findByPage(pageNum,pageSize);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("PageInfo",PageInfo);

        modelAndView.setViewName("role-list");

        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);

        return "redirect:/role/findAll";
    }

     @RequestMapping("/addPermissionListToRoleUI")
    public ModelAndView addPermissionListToRoleUI(Integer roleId){
        List<Permission> permissionList= permissionService.findAll();

        Role role= roleService.findPermissionByRoleId(roleId);

         StringBuilder builder = new StringBuilder();

         for (Permission permission : role.getPermissionList()) {
             builder.append(",");

             builder.append(permission.getId());

             builder.append(",");
         }

         Integer id = role.getId();

         ModelAndView modelAndView = new ModelAndView();

         modelAndView.addObject("permissionList",permissionList);

         modelAndView.addObject("RoleWithPermissionStr",builder.toString());

         modelAndView.addObject("roleId",id);

         modelAndView.setViewName("role-permission-add");

         return modelAndView;
     }

     @RequestMapping("/addPermissionListToRole")
     public String addPermissionListToRole(Integer[] ids,Integer roleId){
        roleService.addPermissionListToRole(ids,roleId);

        return "redirect:/role/findAll";
     }
}
