package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roledao;

    @Override
    public PageInfo<Role> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Role> roleList=roledao.findAll();

        PageInfo<Role> pageInfo = new PageInfo<>(roleList, 4);

        return pageInfo;
    }

    @Override
    public void save(Role role) {
        roledao.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roledao.findAll();
    }

    @Override
    public Role findPermissionByRoleId(Integer roleId) {
        return roledao.findPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionListToRole(Integer[] ids, Integer roleId) {
        roledao.removePermissionListFromRole(roleId);

        for (Integer permissionId : ids) {
            roledao.addPermissionListToRole(permissionId,roleId);
        }
    }
}
