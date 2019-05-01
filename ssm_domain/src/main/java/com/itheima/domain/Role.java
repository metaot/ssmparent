package com.itheima.domain;

import java.util.List;

public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissionList;
    private List<SysTUser> userList;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<SysTUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysTUser> userList) {
        this.userList = userList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", permissionList=" + permissionList +
                ", userList=" + userList +
                '}';
    }
}
