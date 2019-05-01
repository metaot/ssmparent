package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {
    PageInfo<Role> findByPage(Integer pageNum, Integer pageSize);

    void save(Role role);

    List<Role> findAll();

    Role findPermissionByRoleId(Integer roleId);

    void addPermissionListToRole(Integer[] ids, Integer roleId);
}
