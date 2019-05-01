package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {
    PageInfo<Permission> findByPage(Integer pageNum, Integer pageSize);

    List<Permission> findParentPermission();

    void save(Permission permission);

    List<Permission> findAll();
}

