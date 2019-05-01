package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysTUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService  {

    PageInfo<SysTUser> findByPageHelper(Integer pageNum, Integer pageSize);

    void save(SysTUser user);

    boolean checkName(String username);

    SysTUser findByUserId(Integer userId);

    void addRoleListToUser(Integer[] ids, Integer userId);
}
