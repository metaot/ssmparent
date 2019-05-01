package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface PermissionDao {
    @Select("select * from sys_permission")
    List<Permission> findAll();

    @Select("select * from sys_permission where pid=0")
    List<Permission> findParentPermission();

    @Insert("insert into sys_permission values (#{id},#{permissionName},#{url},#{pid})")
    @SelectKey(keyColumn = "id",keyProperty = "id",before = true,resultType = Integer.class,
            statement = "select permission_seq.nextval from dual")
    void save(Permission permission);

    @Select("select p.* from sys_role_permission rp ,sys_permission p where rp.permissionid = p.id and rp.roleId = #{roleId}")
    public List<Permission> findPermissionListByRoleId(Integer roleId);
}
