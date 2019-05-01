package com.itheima.log;

import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogController {

    @Autowired
    LogService logService;

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        SysLog sysLog = new SysLog();

        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();

        User user = (User) authentication.getPrincipal();

        sysLog.setUsername(user.getUsername());

        sysLog.setVisitTime(new Date().toString());

        String ip = request.getRemoteAddr();

        sysLog.setIp(ip);

        Object o = joinPoint.getTarget();

        String className = o.getClass().getName();

        Signature signature = joinPoint.getSignature();

        String methonName = signature.getName();

        sysLog.setMethod(className+"."+methonName);

        logService.save(sysLog);
    }
}
