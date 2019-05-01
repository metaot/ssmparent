package com.itheima.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/test")
public class TestController {

    //从session中获取用户名的第一种方法(方法1还有el表达式版本的,在header.jsp中)
    @RequestMapping("/showUsername")
    public void showUsername(HttpServletRequest request){

        HttpSession session = request.getSession();

        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        Authentication authentication = securityContext.getAuthentication();

        User user = (User) authentication.getPrincipal();

        System.out.println(user.getUsername());

    }

//    从session在中获取用户名的第二种方法(方法2好友el表达式版本的,在header.jsp中)
    @RequestMapping("/showUsername01")
    public void showUsername01(){
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();

        User user = (User) authentication.getPrincipal();

        System.out.println(user.getUsername());
    }
}
